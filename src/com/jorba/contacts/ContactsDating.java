﻿package com.jorba.contacts;

public class ContactsDating {

	private String DatingContent;
	private String DatingUserName;
	private String DatingTime;
	private String DatingPeopleCount;
	private int head;

	public ContactsDating(int head,String DatingUserName,String DatingTime,String DatingPeopleCount,String DatingContent) {
		this.setHead(head);
		this.setDatingUserName(DatingUserName);
		this.setDatingTime(DatingTime);
		this.setDatingPeopleCount(DatingPeopleCount);
		this.setDatingContent(DatingContent);
	}

	public String getDatingContent() {
		return DatingContent;
	}

	public void setDatingContent(String datingContent) {
		DatingContent = datingContent;
	}

	public String getDatingUserName() {
		return DatingUserName;
	}

	public void setDatingUserName(String datingUserName) {
		DatingUserName = datingUserName;
	}

	public String getDatingTime() {
		return DatingTime;
	}

	public void setDatingTime(String datingTime) {
		DatingTime = datingTime;
	}

	public String getDatingPeopleCount() {
		return DatingPeopleCount;
	}

	public void setDatingPeopleCount(String datingPeopleCount) {
		DatingPeopleCount = datingPeopleCount;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}
}
