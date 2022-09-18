package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class CashierView extends BaseView {

	public CashierView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Transactions", "Orders", "Inventories", "Products");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionLogout();

	}
}
