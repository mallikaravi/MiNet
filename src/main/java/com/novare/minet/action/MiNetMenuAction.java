package com.novare.minet.action;

import java.text.ParseException;

import com.novare.minet.model.User;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;

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

	public String getAppHeader() throws ParseException {
		return ServiceUtil.printHeader(getCurrentUser());
	}
}
