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
		printMessage("Enter User full name: ");
		return getUserText();

	}

	public String askPhoneNumber() {
		printMessage("Enter Phone number: ");
		return getUserText();

	}

	public String askAddress() {
		printMessage("Enter Address: ");
		return getUserText();

	}

	public String askEmail() {
		printMessage("Enter Email id: ");
		return getUserText();

	}

	public int askRole(List<?> roles) {
		printMessage("Select the Role: ");
		setMenuOptionsInRow(roles);
		return getSelectedOptionFromMenu(roles.size()) - 1;

	}

	public boolean askForSignUp() {
		printMessage("Do you want to SignUp [Yes/No]: ");
		if (!askConfirmationYesOrNo()) {
			printMessage("Bye !");
			goodBye();
		}
		return true;
	}

}
