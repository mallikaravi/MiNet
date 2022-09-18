package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class InventoryView extends BaseView {

	public InventoryView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("All", "Create Order", "List Of Orders", "Search Order", "Pending Orders");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();
	}
}
