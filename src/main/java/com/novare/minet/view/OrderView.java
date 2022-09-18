package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class OrderView extends BaseView {

	public OrderView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("List", "Create", "Edit", "Delete", "Pending", "Search", "History");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

}
