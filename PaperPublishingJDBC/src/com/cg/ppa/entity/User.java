package com.cg.ppa.entity;

public class User {
	private int userId;
	private String userName;
	private String role;
	private String contactNumber;
	private String emailId;
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(int userId, String userName, String role, String contactNumber, String emailId, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.role = role;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.password = password;
	}

	public User(String userName, String role, String contactNumber, String emailId, String password) {
		super();
		this.userName = userName;
		this.role = role;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", role=" + role + ", contactNumber="
				+ contactNumber + ", emailId=" + emailId + ", password=" + password + "]";
	}

}
