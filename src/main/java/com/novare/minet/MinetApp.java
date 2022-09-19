package com.novare.minet;

import java.text.ParseException;

import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.util.MenuContext;

public class MinetApp {

	public static void main(String[] args) throws ParseException {
		try {
			new WelcomeMenuAction(MenuContext.WELCOME, null);
		} catch (Exception e) {
			System.out.println("Error, Not able to run the application !\n Due to the " + e.getMessage());
		}
	}
}
