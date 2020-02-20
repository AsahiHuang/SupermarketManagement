package com.java.model;

public class UserRegister {
	private int id;
	private String username;
	private String password;
	private boolean identify;
	
	public UserRegister() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRegister(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
