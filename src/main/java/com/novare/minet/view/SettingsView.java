package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class SettingsView  extends BaseView{

	public SettingsView(String menuTitle) {
		super(menuTitle);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Edit Profile", "Change Password", "Delete Profile");
	}

	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();
	}

	public boolean askUserDeletion() {
		printMessage("Do you want to delete the current user [Yes/No]: ");
		return askConfirmationYesOrNo();
	}
	public void printSuccessMessage() {
		printMessage("Data saved successfully !");
	}

}
