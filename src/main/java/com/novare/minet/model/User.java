package com.novare.minet.model;

import java.util.Objects;

public class User extends IdProperty {

	private String fullName;
	private String userName;
	private String passWord;
	private String email;
	private String phoneNumber;
	private String address;
	private Role role;

	public User() {
	}

	/**
	 * @param fullName
	 * @param userName
	 * @param passWord
	 * @param email
	 * @param phoneNumber
	 * @param address
	 * @param role
	 */
	public User(String fullName, String userName, String passWord, String email, String phoneNumber, String address,
			Role role) {
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;

	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		return super.hashCode() + Objects.hash(getFullName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

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
