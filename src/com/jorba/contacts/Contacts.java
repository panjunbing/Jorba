package com.jorba.contacts;

public class Contacts {

	private String string;
	private String number;
	private int head;

	public Contacts( String number,String string, int head) {
		this.string = string;
		this.number = number;
		this.head = head;

	}
	
	public String getNumber () {
		return number;
	}
	
	public int getHead () {
		return head;
	}

	public String getString() {
		return string;
	}

}
