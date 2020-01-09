package PMS.client;


import PMS.db.DBAccount;
import PMS.entity.Account;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;


public class Client implements Runnable {
    // 常量 //////////////////////////////////////////////////////////////////
    private static final String HOST = "127.0.0.1"; // 服务器地址
    private static final int PORT = 6271; // 服务器所在端口

    // 变量 //////////////////////////////////////////////////////////////////
    private Account accCurr;                // 登录账户
    private Thread thCurr;                  // 当前线程
    private AtomicBoolean bKeepOutputStreamMsgLoop;   // 消息循环状态标志

    private AtomicBoolean bConnected; // 已连接标志，如果为false，则以下变量不能在除run方法外的任何方法被使用
    private Socket socket;                  // Socket对象，仅在bConnected为true时可用
    private ObjectInputStream input;        // 经包装的socket输入流，仅在bConnected为true时可用
    private ObjectOutputStream output;      // 经包装的socket输出流，仅在bConnected为true时可用
    private ClientInputStreamHandler clientInputStreamHandler;  // 输入流处理器，仅在bConnected为true时可用

    private String cmdPrefix;       // 命令行前缀
    private AtomicBoolean bFinished; // 客户端运行结束标志

    private ClientUpdateHandler clientUpdateHandler;
    private ClientForcedOfflineHandler clientForcedOfflineHandler;

    public void setClientUpdateHandler(ClientUpdateHandler clientUpdateHandler) {
        this.clientUpdateHandler = clientUpdateHandler;
    }

    public void setClientForcedOfflineHandler(ClientForcedOfflineHandler clientForcedOfflineHandler) {
        this.clientForcedOfflineHandler = clientForcedOfflineHandler;
    }

    public Client(Account currAcc) {
        accCurr = currAcc;
        thCurr = new Thread(this);

        bKeepOutputStreamMsgLoop = new AtomicBoolean(true);
        bConnected = new AtomicBoolean(false);
        socket = null;
        input = null;
        output = null;
        clientInputStreamHandler = null;

        cmdPrefix = "[client]";
        bFinished = new AtomicBoolean(false);
    }

    // 公共方法：启动客户端线程
    // 基本上由外部调用
    public boolean startAndConnect() {
        boolean state = false;

        if (!thCurr.isAlive()) {
            thCurr.start();
            // 等待启动完成
            state = waitForStartFinished();
        } else {
            printf("客户端线程已经启动\n");
        }

        return state;
    }

    // 公共方法：停止客户端
    // 具体包括停止输入流循环和输出流循环
    public void disconnectAndStop() {
        if (clientInputStreamHandler != null) {
            // 先发送断开连接消息给服务器
            sendMsgDisconnect();
            // 停止输入流消息循环
            clientInputStreamHandler.stopInputStreamMsgLoop();
        }
        // 停止输出流消息循环
        stopOutputStreamMsgLoop();
    }

    // 内部方法：仅停止消息循环，不发送断开连接信息
    private void stop() {
        if (clientInputStreamHandler != null) {
            // 停止输入流消息循环
            clientInputStreamHandler.stopInputStreamMsgLoop();
        }
        // 停止输出流消息循环
        stopOutputStreamMsgLoop();
    }

    private boolean waitForStartFinished() {
        boolean state = true;

        // 等待连接成功，当bFinished标志被设置时停止等待
        while (!bConnected.get()) {
            if (bFinished.get()) {
                state = false;
                break;
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException ignored) {
            }
        }
        return state;
    }

    // 内部方法：停止输出流消息循环
    private void stopOutputStreamMsgLoop() {
        bKeepOutputStreamMsgLoop.set(false);
    }

    // 打印消息
    private void printf(String format, Object... args) {
        System.out.print(String.format(cmdPrefix + format, args));
    }

    // 消息：发起连接
    // 不允许外部调用
    private void sendMsgConnect() {
        if (bConnected.get()) {
            try {
                output.writeUTF("客户端请求连接"); // 发送报文通知服务器，请求连接
                output.writeObject(accCurr); // 发送用户标识
            } catch (IOException e) {
                printf("sendMsgConnect发生异常：\n");
                e.printStackTrace();
            }
        } else {
            printf("未连接\n");
        }
    }

    // 消息：断开连接
    // 不允许外部调用
    private void sendMsgDisconnect() {
        if (bConnected.get()) {
            try {
                output.writeUTF("客户端断开连接"); // 发送报文通知服务器，通知断开连接
                output.flush(); // 必须进行刷新，否则可能在发送前连接就被关闭了
            } catch (IOException e) {
                printf("sendMsgDisconnect发生异常：\n");
                e.printStackTrace();
            }
        } else {
            printf("未连接\n");
        }
    }

    // 消息：断开连接
    // 不允许外部调用
    private void sendMsgForcedDisconnect() {
        if (bConnected.get()) {
            try {
                output.writeUTF("客户端因强制下线断开连接"); // 发送报文通知服务器，通知断开连接
                output.flush(); // 必须进行刷新，否则可能在发送前连接就被关闭了
            } catch (IOException e) {
                printf("sendMsgForcedDisconnect发生异常：\n");
                e.printStackTrace();
            }
        } else {
            printf("未连接\n");
        }
    }

    // 消息：通知别的客户端接收消息
    public void sendMsgNotifyOtherReceiveMessage(Account receiverAcc) {
        if (bConnected.get()) {
            try {
                output.writeUTF("客户端通知别的客户端接收消息");
                output.writeObject(receiverAcc);
                output.flush(); // 必须刷新，否则
            } catch (IOException e) {
                printf("sendMsgNotifyOtherReceiveMessage发生异常：\n");
                e.printStackTrace();
            }
        } else {
            printf("未连接\n");
        }
    }

    // run方法
    @Override
    public void run() {

        printf("客户端启动...\n");

        try {
            // 创建socket连接
            socket = new Socket(HOST, PORT);
            printf("成功连接服务器\n");

            // 包装socket的输入输出流
            // 服务器先创建输入流，则客户端必须先创建输出流，否则会照成阻塞
            // 详情：http://www.voidcn.com/article/p-vidlrdco-bcb.html
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            // 启动输入流处理器（另一个线程）
            clientInputStreamHandler = new ClientInputStreamHandler(input);

            try {
                bConnected.set(true); // 设置连接状态，连接

                // 首先发送连接消息
                sendMsgConnect();

                while (bKeepOutputStreamMsgLoop.get()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignored) {
                    }
                }
            } finally {
                bConnected.set(false); // 设置连接状态，关闭
            }

        } catch (ConnectException e) {
            printf("连接失败\n");
            socket = null;
        } catch (IOException e) {
            printf("连接发生异常：\n");
            e.printStackTrace();
        } finally {
            bFinished.set(true);
            try {
                if (socket != null)
                    socket.close();
                if (input != null)
                    input.close();
                if (output != null)
                    output.close();
            } catch (Exception ignored) {
            }
        }
    }

    private class ClientInputStreamHandler implements Runnable {

        // Socket的输入流
        private ObjectInputStream input;
        // 原子布尔对象，用于通知输入流处理器退出
        private AtomicBoolean bKeepInputStreamMsgLoop;

        /**
         * @param input 输入流
         */
        private ClientInputStreamHandler(ObjectInputStream input) {
            this.input = input;
            this.bKeepInputStreamMsgLoop = new AtomicBoolean(true);

            // 直接创建并启动线程
            new Thread(this).start();
        }

        // 私有方法：停止输入流消息循环
        private void stopInputStreamMsgLoop() {
            bKeepInputStreamMsgLoop.set(false);
        }

        @Override
        public void run() {

            try {
                printf("消息接收器启动...\n");

                String header;
                String msg;

                while (bKeepInputStreamMsgLoop.get()) { // 如果外部信号量设置，也就是通知结束，则退出循环
                    // 读取报头
                    header = input.readUTF();
                    printf("事件：%s\n", header);
                    switch (header) {
                        case "服务端通知客户端更新":
                            if (clientUpdateHandler != null) {
                                clientUpdateHandler.handler();
                            }
                            break;
                        case "服务端通知关闭连接":
                            msg = input.readUTF();
                            printf("服务端关闭连接：%s\n", msg);
                            stop();
                            break;
                        case "服务端通知客户端强制下线关闭连接":
                            if (clientForcedOfflineHandler != null) {
                                clientForcedOfflineHandler.handler();
                            }
                            sendMsgForcedDisconnect();
                            break;
                    }
                }
            } catch (Exception e) {
                printf("连接已断开：%s\n", e.getMessage());
//                e.printStackTrace();
                // 通知输出流消息循环退出
                stopOutputStreamMsgLoop();
            }
        }
    }

    // 测试
    public static void main(String[] args) throws InterruptedException {
        Account acc = DBAccount.get("normalStaff");
        Client c = new Client(acc);
        c.startAndConnect();

        Account recvAcc = DBAccount.get("personnelStaff");
        Thread.sleep(2000);
        c.sendMsgNotifyOtherReceiveMessage(recvAcc);

        Thread.sleep(2000);
        c.sendMsgDisconnect();
        c.disconnectAndStop();
    }

}
