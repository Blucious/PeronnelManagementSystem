package PMS.db;

import PMS.db.util.DBAccessUtil;
import PMS.db.util.ResultSetHandler;
import PMS.entity.Message;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public final class DBMessage {

    //私有空构造方法,保证本类不能够被实例化。
    private DBMessage() {
    }

    /**
     * 添加消息，消息编号由数据库设置
     */
    public static boolean add(Message msg) {
        // 查询语句
        String sql = "INSERT INTO message " +
                "(msgSenderAccName, msgReceiverAccName, " +
                "msgSendTime, msgMessage) " +
                "VALUES (?,?,?,?)";
        // 执行查询
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        msg.getSenderAccName(),
                        msg.getReceiverAccName(),
                        msg.getSendTime(),
                        msg.getMessage());
        // 异常处理
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    /**
     * 通过消息编号删除消息
     */
    public static boolean delete(int no) {
        String sql = "DELETE FROM message WHERE msgNo=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql, no);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    /**
     * 通过发送者编号删除消息
     */
    public static boolean deleteBySenderAccNo(String no) {
        String sql = "DELETE FROM message " +
                "WHERE msgSenderAccName=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql, no);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }
        return ur.state;
    }

    /**
     * 通过接受者编号删除消息
     */
    public static boolean deleteByReceiverAccNo(String no) {
        String sql = "DELETE FROM message " +
                "WHERE msgReceiverAccName=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql, no);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }
        return ur.state;
    }

    /**
     * 删除全部消息
     */
    public static boolean deleteAll() {
        String sql = "DELETE FROM message";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }
        return ur.state;
    }

    /**
     * 消息不用修改
     */

    // 通用多记录结果集处理器
    private final static ResultSetHandler rshMultiple = (ResultSet rs) -> {
        List<Message> l = new LinkedList<>();
        Message m;
        while (rs.next()) {
            m = new Message();
            m.setNo(rs.getInt(1));
            m.setSenderAccName(rs.getString(2));
            m.setReceiverAccName(rs.getString(3));
            m.setSendTime(rs.getDate(4));
            m.setMessage(rs.getString(5));
            l.add(m);
        }
        return l;
    };

    /**
     * 通过发送者编号字查询消息
     */
    @SuppressWarnings("unchecked cast")
    public static Iterator<Message> getBySenderAccNo(String no) {
        String sql = "SELECT * FROM message " +
                "WHERE msgSenderAccName=?";
        // 执行查询
        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rshMultiple, no);
        // 如果有异常则处理异常
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }
        // 返回查询结果
        return ((List<Message>) qr.result).iterator();
    }

    /**
     * 通过接受者编号字查询消息
     */
    @SuppressWarnings("unchecked cast")
    public static Iterator<Message> getByReceiverAccNo(String no) {
        String sql = "SELECT * FROM message " +
                "WHERE msgReceiverAccName=?";
        // 执行查询
        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rshMultiple, no);
        // 如果有异常则处理异常
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }
        // 返回查询结果
        return ((List<Message>) qr.result).iterator();
    }

    /**
     * 查询全部消息
     */
    @SuppressWarnings("unchecked cast")
    public static Iterator<Message> getAll() {
        String sql = "SELECT * FROM message";
        // 执行查询
        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rshMultiple);
        // 如果有异常则处理异常
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }
        // 返回查询结果
        return ((List<Message>) qr.result).iterator();
    }


    // 测试
    public static void main(String[] args) {
        System.out.println("查询全部：");
        for (Iterator it = getAll(); it.hasNext(); ) {
            Message t = (Message) it.next();
            System.out.println(t);
        }

        System.out.println("添加+查询全部：");
        add(new Message(
                "test1", "test2", "testMsg1->2"));
        add(new Message(
                "test2", "test1", "testMsg2->1"));
        for (Iterator it = getAll(); it.hasNext(); ) {
            Message t = (Message) it.next();
            System.out.println(t);
        }

        System.out.println("通过发送者编号删除+查询全部：");
        deleteBySenderAccNo("test1");
        deleteBySenderAccNo("test2");
        for (Iterator it = getAll(); it.hasNext(); ) {
            Message t = (Message) it.next();
            System.out.println(t);
        }

        System.out.println("添加+查询全部：");
        add(new Message(
                "test1", "test2", "testMsg1->2"));
        add(new Message(
                "test2", "test1", "testMsg2->1"));
        for (Iterator it = getAll(); it.hasNext(); ) {
            Message t = (Message) it.next();
            System.out.println(t);
        }

        System.out.println("通过接受者编号删除+查询全部：");
        deleteBySenderAccNo("test1");
        deleteBySenderAccNo("test2");
        for (Iterator it = getAll(); it.hasNext(); ) {
            Message t = (Message) it.next();
            System.out.println(t);
        }

        System.out.println("添加+查询全部：");
        Message newMsg = new Message(
                "test1", "test2", "testMsg1->2");
        add(newMsg);
        for (Iterator it = getAll(); it.hasNext(); ) {
            Message t = (Message) it.next();
            System.out.println(t);
        }

        System.out.println("通过编号删除+查询全部：");
        Message msg = getBySenderAccNo("test1").next();
        delete(msg.getNo());
        for (Iterator it = getAll(); it.hasNext(); ) {
            Message t = (Message) it.next();
            System.out.println(t);
        }

//        System.out.println(getBySenderAccNo("测试职位"));
//
//        System.out.println("修改+查询：");
//        update("测试职位", new Title("测试职位2"));
//        System.out.println(get("测试职位2"));
//
//        System.out.println("删除+查询全部：");
//        delete("测试职位2");
//
//        for (Iterator it = getAll(); it.hasNext(); ) {
//            Title t = (Title) it.next();
//            System.out.println(t);
//        }
    }
}
