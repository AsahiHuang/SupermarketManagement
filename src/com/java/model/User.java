package com.java.model;


/**
 * 用户实体
 */


public class User {
	
	private int id;
	private String userName;
	private String password;
	private boolean identify;
	
	
	
	public User(int id, String userName, String password, boolean identify) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.identify = identify;
	}

	public User(int id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public User(String userName, String password,boolean identify) {
		super();
		this.userName = userName;
		this.password = password;
		this.identify = identify;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIdentify() {
		return this.identify;
	}
	public void setIdentify(Boolean identify) {
		this.identify = identify;
	}
}
