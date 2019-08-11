package com.xinyunjia.customerservice.entity;

import java.sql.Timestamp;

public class Message {
	private int messageId;
    private String fromName;
    private String toName;
    private String messageText;
    private Timestamp messageDate;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public Timestamp getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Timestamp messageDate) {
		this.messageDate = messageDate;
	}
	public Message() {
		super();
	}
	public Message(int messageId, String fromName, String toName,
			String messageText, Timestamp messageDate) {
		super();
		this.messageId = messageId;
		this.fromName = fromName;
		this.toName = toName;
		this.messageText = messageText;
		this.messageDate = messageDate;
	}
    
}
