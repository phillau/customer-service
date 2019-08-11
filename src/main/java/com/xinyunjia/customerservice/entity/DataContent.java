package com.xinyunjia.customerservice.entity;

import java.io.Serializable;

/**
 * @author liufei
 */
public class DataContent implements Serializable {

	private static final long serialVersionUID = 8775443715752177318L;

	private Integer action;		// 动作类型
	private ChatMsg chatMsg;	// 用户的聊天内容entity
	private String extend;		// 扩展字段
	private boolean newUser;  //是否是第一次咨询的客户
	
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public ChatMsg getChatMsg() {
		return chatMsg;
	}
	public void setChatMsg(ChatMsg chatMsg) {
		this.chatMsg = chatMsg;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}

	public boolean isNewUser() {
		return newUser;
	}

	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}
}
