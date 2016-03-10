package com.jorba.data;

public class Room {
	private String RoomName;
	private String Name;
	private String member[];
	private String Content;
	
	public String getRoomName() {
		return RoomName;
	}
	public void setRoomName(String roomName) {
		RoomName = roomName;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String[] getMember() {
		return member;
	}
	public void setMember(String[] member) {
		this.member = member;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
}
