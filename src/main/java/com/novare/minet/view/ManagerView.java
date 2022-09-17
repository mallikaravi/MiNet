package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class ManagerView extends BaseView {

	public ManagerView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("View OrderList", "Review Inventory", "Save Transactions");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();
	}

}
