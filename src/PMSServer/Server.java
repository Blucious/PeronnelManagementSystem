package PMSServer;

import PMS.entity.Account;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;


public class Server
        implements Runnable {
    // 静态变量
    private static final int PORT = 6271;

    // 变量
    private Thread thCurr;                // 当前进程
    private AtomicBoolean bKeepMainLoop;  // 状态，是否保持主循环

    public Server() {
        thCurr = new Thread(this);
        bKeepMainLoop = new AtomicBoolean(true);
    }

    // 公共方法：启动服务器（也就是启动新线程）
    public void start() {
        if (!thCurr.isAlive()) {
            thCurr.start();
        } else {
            System.out.println("[main]服务器已启动");
        }
    }

    // 公共方法：停止服务器
//    public void stop() {
//        bKeepMainLoop.set(false);
//    }

    private static class SocketAndStream {
        Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;

        public SocketAndStream(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
            this.socket = socket;
            this.input = input;
            this.output = output;
        }
    }

    // Runnable接口
    @Override
    public void run() {
        // 并发映射，存储用户实体对象到Socket对象的映射
        // 用于判断用户是否登录，以及在用户间发送消息时进行索引
        ConcurrentHashMap<Account, SocketAndStream> clientIdentityToSocket = new ConcurrentHashMap<>();
        // 创建服务器Socket
        ServerSocket serverSocket = null;

        try {
            System.out.println("[main]服务器启动...");
            serverSocket = new ServerSocket(PORT);

            while (bKeepMainLoop.get()) {
                // 一旦有堵塞, 则表示服务器与客户端获得了连接
                Socket client = serverSocket.accept();
                System.out.printf("[main]已与%s建立连接\n", client.getRemoteSocketAddress());

                // 处理这次连接
                new ServerSocketHandler(client, clientIdentityToSocket);
            }
        } catch (Exception e) {
            System.out.println("[main]服务器发生异常:");
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                System.out.println("[main]错误：ServerSocket关闭失败");
            }
        }
    }

    private static class ServerSocketHandler
            implements Runnable {

        private Socket socket; // 当前要处理的socket
        private ConcurrentHashMap<Account, SocketAndStream> clientIdentityToSocket;
        ObjectInputStream input; // 经包装的Socket的输入流
        ObjectOutputStream output; // 经包装的Socket的输出流
        AtomicBoolean bKeepInputStreamMsgLoop; // 输入流消息循环状态标志

        private String cmdPrefix; // 命令行前缀


        ServerSocketHandler(Socket socketClient, ConcurrentHashMap<Account, SocketAndStream> clientIdentityToSocket) {
            socket = socketClient;
            this.clientIdentityToSocket = clientIdentityToSocket;

            input = null;
            output = null;
            bKeepInputStreamMsgLoop = new AtomicBoolean(true);

            // 创建线程
            Thread th = new Thread(this);

            // 设置该线程的命令行前缀
            cmdPrefix = String.format("[th-%s]", th.getId());

            // 启动线程
            th.start();
        }

        // 私有方法：停止输入流消息循环
        private void stopInputStreamMsgLoop() {
            bKeepInputStreamMsgLoop.set(false);
        }

        private void handlerConnectionClosing(String msg) {
            try {
                output.writeUTF("服务端通知关闭连接");
                output.writeUTF(msg);
                output.flush(); // 必须刷新
            } catch (IOException e) {
                printf("sendMsgConnectionClosing发生异常：\n");
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            printf("处理器启动...\n");

            Account accCurr = null; // 用户标识对象
            input = null; // 经包装的Socket的输入流
            output = null; // 经包装的Socket的输出流
            AtomicBoolean bForcedDisconnect = new AtomicBoolean(false);

            try {
                input = new ObjectInputStream(socket.getInputStream());
                output = new ObjectOutputStream(socket.getOutputStream());


                while (bKeepInputStreamMsgLoop.get()) {
                    Account accReceiver;
                    String headLine = input.readUTF();
                    printf("事件：%s\n", headLine);
                    switch (headLine) {
                        case "客户端请求连接":
                            accCurr = (Account) input.readObject();
                            if (accCurr == null) {
                                printf("客户端表示对象无效，Handler退出\n");

                                handlerConnectionClosing("用户标识无效");
                                return;
                            }

                            printf("【%s】连接...\n", accCurr.getName());
                            // 处理重复连接问题，如果已经在线则不允许连接，直接退出当前Handler
                            if (clientIdentityToSocket.containsKey(accCurr)) {
//                                printf("【%s】连接失败，因为与其具有同一标识的客户端已经在线，Handler退出\n",
//                                        accCurr.getName());
//                                handlerConnectionClosing("用户已经在线");
                                printf("【%s】从另一客户端登录，通知原来客户端强制下线\n", accCurr.getName());
                                SocketAndStream ss = clientIdentityToSocket.get(accCurr);
                                ss.output.writeUTF("服务端通知客户端强制下线关闭连接");
                                ss.output.flush();
                                clientIdentityToSocket.remove(accCurr);
                            }
                            SocketAndStream ss = new SocketAndStream(socket, input, output);
                            clientIdentityToSocket.put(accCurr, ss);
                            printf("当前在线用户数量%d：\n%s\n",
                                    clientIdentityToSocket.size(), clientIdentityToSocket);
                            break;
                        case "客户端通知别的客户端接收消息":
                            accReceiver = (Account) input.readObject();
                            if (accReceiver == null) {
                                printf("用户标识无效");
                                break;
                            }

                            printf("【%s】要通知【%s】更新\n", accCurr.getName(), accReceiver.getName());
                            if (clientIdentityToSocket.containsKey(accReceiver)) {
                                printf("目标在线，发送消息...\n");
                                SocketAndStream receiver = clientIdentityToSocket.get(accReceiver);
                                receiver.output.writeUTF("服务端通知客户端更新");
                                receiver.output.flush();
                            } else {
                                printf("目标不在线\n");
                            }
                            break;
                        case "客户端断开连接":
                            printf("【%s】断开连接\n", accCurr.getName());
                            handlerConnectionClosing("用户断开连接");
                            stopInputStreamMsgLoop();
                            break;
                        case "客户端因强制下线断开连接":
                            printf("【%s】断开连接\n", accCurr.getName());
                            handlerConnectionClosing("用户断开连接");
                            stopInputStreamMsgLoop();
                            bForcedDisconnect.set(true);
                            break;
                    }
                }

            } catch (IOException e) {
//                printf("发生异常，与【%s】的连接连接中断，异常信息：\n", accCurr.getName());
//                e.printStackTrace();
                printf("与【%s】的连接连接中断，原因：%s\n",
                        accCurr.getName(), e.getMessage());
//                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("发生异常ClassNotFoundException：");
                e.printStackTrace();
            } finally {
                // 将用户标识从map里移除，表明该用户已下线
                // 如果是被强制断开连接，用户标识已经从map被移除了
                if (accCurr != null && !bForcedDisconnect.get()) {
                    clientIdentityToSocket.remove(accCurr);
                }
                try {
                    if (socket != null)
                        socket.close();
                    if (input != null)
                        input.close();
                    if (output != null)
                        output.close();
                } catch (Exception e) {
                    printf("服务端 finally 异常:\n");
                    e.printStackTrace();
                }
            } // finally结束

        } // run结束

        // 做一个输出方法，可以自动加上前缀
        private void printf(String format, Object... args) {
            System.out.print(String.format(cmdPrefix + format, args));
        }
    } // ServerSocketHandler结束


    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
} // PMSServer结束

