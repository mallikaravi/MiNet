package com.novare.minet.action;

import com.novare.minet.model.User;
import com.novare.minet.util.MenuContext;

public class BaseMenuAction {
	private User currentUser;

	public BaseMenuAction(MenuContext context, User currentUser) {
		super();
		this.currentUser = currentUser;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
