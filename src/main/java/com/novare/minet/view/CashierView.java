package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class CashierView extends BaseView {

	public CashierView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Inventory Status", "My Transactions", "My Orders", "CounterSale", "Return Product",
				"Search Transaction", "Delete Transaction");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}
}
