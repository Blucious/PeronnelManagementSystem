package PMS.entity;

import java.util.Date;

public class Message {
    private int no;
    private String senderAccName;
    private String receiverAccName;
    private Date sendTime;
    private String message;

    public Message() {
    }

    // 新建消息，日期为当前日期，不设置no，no由数据库设置
    public Message(String senderAccName, String receiverAccName, String message) {
        this(senderAccName, receiverAccName, new Date(), message);
    }

    // 新建消息并指定日期，不设置no，no由数据库设置
    public Message(String senderAccName, String receiverAccName, Date sendTime, String message) {
        setSenderAccName(senderAccName);
        setReceiverAccName(receiverAccName);
        setSendTime(sendTime);
        setMessage(message);
    }

    // 放入已有的消息，设置no
    public Message(int no, String senderAccName, String receiverAccName, Date sendTime, String message) {
        setNo(no);
        setSenderAccName(senderAccName);
        setReceiverAccName(receiverAccName);
        setSendTime(sendTime);
        setMessage(message);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSenderAccName() {
        return senderAccName;
    }

    public void setSenderAccName(String senderAccName) {
        this.senderAccName = senderAccName;
    }

    public String getReceiverAccName() {
        return receiverAccName;
    }

    public void setReceiverAccName(String receiverAccName) {
        this.receiverAccName = receiverAccName;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "no=" + no +
                ", senderAccName='" + senderAccName + '\'' +
                ", receiverAccName='" + receiverAccName + '\'' +
                ", sendTime=" + sendTime +
                ", message='" + message + '\'' +
                '}';
    }
}
