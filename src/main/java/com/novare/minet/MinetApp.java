package com.novare.minet;

import java.io.IOException;

import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.util.MenuContext;

public class MinetApp {
	public static void main(String[] args) throws IOException {
		User currentUser = null;
		try {
			new WelcomeMenuAction(MenuContext.MAIN, currentUser);
		} catch (Exception e) {
			System.out.println("Error, Not able to run the application !\n Due to the " + e.getMessage());
		}
	}

}
