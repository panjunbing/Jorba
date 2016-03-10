package com.jorba.contacts;

/**
 * 一个消息的JavaBean
 * 
 * @author way
 * 
 */

public class ContactsDatingChat {
	
	private int head;
	private String message;																	//消息内容
	private int type;																			//  0收到的消息 1发送的消息 2为时间
	private String time;

	public ContactsDatingChat(int head,String message, int type ) {
		setHead(head);
		setMessage(message);
		setType(type);
	}
	
	public ContactsDatingChat(String time){
		setTime(time);
		type = 2;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
