package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class CashierView extends MinetView {

	public CashierView(String menuTitle) {
		super(menuTitle);
	}

	public CashierView(String appHeader, String menuTitle) {
		super(appHeader, menuTitle);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Transactions", "Products", "Orders", "Settings");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionLogout();

	}
}
