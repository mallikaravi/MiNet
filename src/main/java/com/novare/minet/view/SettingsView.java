package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class SettingsView extends MinetView {

	public SettingsView(String menuTitle) {
		super(menuTitle);
	}

	public SettingsView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Edit Profile", "Change Password", "Delete Profile");
	}

	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();
	}

	public boolean askUserDeletion() {
		return askConfirmationYesOrNo("Do you want to delete the current user [Yes/No]: ");
	}

	public void printSuccessMessage() {
		printMessage("Data saved successfully !");
	}

}
