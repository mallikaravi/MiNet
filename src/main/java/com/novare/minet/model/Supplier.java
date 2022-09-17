package com.novare.minet.model;

import java.util.Objects;

public class Supplier extends IdProperty {
	private String name;
	private String address;
	private String email;
	private String phoneNumber;

	/**
	 * 
	 */
	public Supplier() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param email
	 * @param phoneNumber
	 */
	public Supplier( String name, String address, String email, String phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		return Objects.hash(address, email, getId(), name, phoneNumber);
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
		Supplier other = (Supplier) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email) && getId() == other.getId() 
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Supplier [id=" + getId() + ", name=" + name + ", address=" + address + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}

}
