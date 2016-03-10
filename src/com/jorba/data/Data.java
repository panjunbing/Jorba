package com.jorba.data;

public class Data {
	private static String username = null;
	private static String url = "http://192.168.1.110:8080";
	
	public static String getUsername() {
		return username;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		Data.url = url;
	}

	public static void setUsername(String username) {
		Data.username = username;
	}
	
}
