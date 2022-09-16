package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class WelcomeView extends BaseView {

	public WelcomeView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Admin", "Cashier", "Manager");
	}

	public void printNavigationMenu() {
		PrintHandler.optionQuit();
	}

}
