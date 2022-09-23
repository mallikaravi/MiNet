package com.novare.minet.model;

import java.util.Objects;

import com.novare.minet.util.Ids;

public class Supplier extends IdProperty {
	private String name;
	private String address;
	private String email;
	private String phoneNumber;

	public Supplier() {
		super();
	}

	public Supplier(String name, String address, String email, String phoneNumber) {
		this();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int supplierId = id.getSupplierId();
		setId(supplierId);
		id.close();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		return getId() == other.getId() && getName().equals(other.getName());
	}

	@Override
	public String toString() {
		return name.toUpperCase() + " [" + address + " @ " + phoneNumber + "]";
	}
}
