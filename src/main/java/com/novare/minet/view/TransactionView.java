package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class TransactionView extends BaseView {

	public TransactionView(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Start", "Return", "My Transactions", "Search");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

}
