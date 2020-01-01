package PMS.entity;

import java.sql.Timestamp;

public class Message {
    private String senderAccNo;
    private String receiverAccNo;
    private Timestamp sendTime;
    private String message;

    public Message() {
    }

    public Message(String senderAccNo, String receiverAccNo, Timestamp sendTime, String message) {
        this.senderAccNo = senderAccNo;
        this.receiverAccNo = receiverAccNo;
        this.sendTime = sendTime;
        this.message = message;
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

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
