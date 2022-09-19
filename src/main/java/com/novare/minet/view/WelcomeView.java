package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class WelcomeView extends BaseView {

	public WelcomeView(String title) {
		super(title);
	}

	public WelcomeView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Login", "SignUp");
	}

	public void printNavigationMenu() {
		PrintHandler.optionQuit();
	}

	public String askUserFullName() {
		return getUserText("Enter User full name: ");

	}

	public String askPhoneNumber() {
		return getUserText("Enter Phone number: ");

	}

	public String askAddress() {
		return getUserText("Enter Address: ");

	}

	public String askEmail() {
		return getUserText("Enter Email id: ");

	}

	public int askRole(List<?> roles) {
		printMessage("Select the Role: ");
		setMenuOptionsInRow(roles);
		return getSelectedOptionFromMenu(roles.size()) - 1;

	}

	public boolean askForSignUp() {
		if (!askConfirmationYesOrNo("Do you want to SignUp [Yes/No]: ")) {
			printMessage("Bye !");
			goodBye();
		}
		return true;
	}

}
