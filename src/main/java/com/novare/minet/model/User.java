package com.novare.minet.model;

public class User {
	private int id;
	private String fullName;
	private String userName;
	private String passWord;
	
	public User(int id, String fullName, String userName, String passWord) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", fullName=" + fullName + ", userName=" + userName + ", passWord=" + passWord
				+ "]";
	}
	
	

}
