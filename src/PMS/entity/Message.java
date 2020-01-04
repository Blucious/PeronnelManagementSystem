package PMS.entity;

import java.util.Date;

public class Message {
    private int no;
    private String senderAccNo;
    private String receiverAccNo;
    private Date sendTime;
    private String message;

    public Message() {
    }

    // 新建消息，日期为当前日期，不设置no，no由数据库设置
    public Message(String senderAccNo, String receiverAccNo, String message) {
        this(senderAccNo, receiverAccNo, new Date(), message);
    }

    // 新建消息并指定日期，不设置no，no由数据库设置
    public Message(String senderAccNo, String receiverAccNo, Date sendTime, String message) {
        setSenderAccNo(senderAccNo);
        setReceiverAccNo(receiverAccNo);
        setSendTime(sendTime);
        setMessage(message);
    }

    // 放入已有的消息，设置no
    public Message(int no, String senderAccNo, String receiverAccNo, Date sendTime, String message) {
        setNo(no);
        setSenderAccNo(senderAccNo);
        setReceiverAccNo(receiverAccNo);
        setSendTime(sendTime);
        setMessage(message);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSenderAccNo() {
        return senderAccNo;
    }

    public void setSenderAccNo(String senderAccNo) {
        this.senderAccNo = senderAccNo;
    }

    public String getReceiverAccNo() {
        return receiverAccNo;
    }

    public void setReceiverAccNo(String receiverAccNo) {
        this.receiverAccNo = receiverAccNo;
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
                ", senderAccNo='" + senderAccNo + '\'' +
                ", receiverAccNo='" + receiverAccNo + '\'' +
                ", sendTime=" + sendTime +
                ", message='" + message + '\'' +
                '}';
    }
}
