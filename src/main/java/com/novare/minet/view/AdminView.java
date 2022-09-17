package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class AdminView extends BaseView {

	public AdminView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Payments", "cashFlow", "Profits", "Review Transactions");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

}
