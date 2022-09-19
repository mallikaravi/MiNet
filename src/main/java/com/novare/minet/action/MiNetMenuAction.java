package com.novare.minet.action;

import com.novare.minet.model.User;
import com.novare.minet.util.MenuContext;

public class MiNetMenuAction {
	private User currentUser;

	public MiNetMenuAction(MenuContext context, User currentUser) {
		super();
		this.currentUser = currentUser;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getAppHeader() {
		String fullName = currentUser == null ? "xxxxxxxxxxx" : currentUser.getFullName();
		String name = currentUser == null ? "xxxxxxxxxxx" : currentUser.getRole().name();

		StringBuilder builder = new StringBuilder();
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));
		builder.append(String.format("* %-68s * %n", "Welcome to MiNet WareHouse Management System"));
		builder.append(String.format("* Name: %-62s * %n", fullName.toUpperCase()));
		builder.append(String.format("* Role: %-62s * %n", name.toUpperCase()));
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));
		return builder.toString();
	}
}
