package com.ibtech.sales.inventory.entity;

public class AdminUser {
	
	private long adminUserId;
	private String userName;
	private String userPassword;
	
	public AdminUser(long adminUserId, String userName, String userPassword) {
		super();
		this.adminUserId = adminUserId;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public AdminUser() {
	
	}

	public long getUserId() {
		return adminUserId;
	}

	public void setUserId(long userId) {
		this.adminUserId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	
}
