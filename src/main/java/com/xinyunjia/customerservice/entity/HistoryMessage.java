package com.xinyunjia.customerservice.entity;

public class HistoryMessage {
    /**
     * send : customer_service_1
     * msg : hello
     * loc : r
     */
    /**
     * 发送方id
     */
    private String send;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 展示在会话框左边还是右边
     */
    private String loc;

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
