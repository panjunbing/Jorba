package com.jorba.contacts;

public class ContactsDatingInvitations {

	private int head;
	private String name;
	private String time;

	public ContactsDatingInvitations(int head,String name,String time) {
		this.setHead(head);
		this.setName(name);
		this.setTime(time);
		// TODO 自动生成的构造函数存根
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


}
