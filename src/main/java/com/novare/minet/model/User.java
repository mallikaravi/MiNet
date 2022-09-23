package com.novare.minet.model;

import java.util.Objects;

import com.novare.minet.util.Ids;

public class User extends IdProperty {

	private String fullName;
	private String userName;
	private String passWord;
	private String email;
	private String phoneNumber;
	private String address;
	private Role role;

	public User() {
		super();
	}

	public User(String fullName, String userName, String passWord, String email, String phoneNumber, String address,
			Role role) {
		this();
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int userId = id.getUserId();
		setId(userId);
		id.close();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + Objects.hash(getFullName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return (getId() == other.getId() && getFullName().equals(other.getFullName()));
	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ", fullName=" + fullName + ", userName=" + userName + ", passWord=" + passWord
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", role=" + role + "]";
	}

}
