package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class OrderView extends BaseView {

	public OrderView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Order List", "Create Order", "Edit Order", "Deleate Order", "Pending Orders", "Search Order",
				"History");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

}
