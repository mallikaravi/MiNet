package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class AdminView extends MinetView {

	public AdminView(String title) {
		super(title);
	}

	public AdminView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Transaction", "Products", "Suppliers", "Order", "Payments", "Cash Flow", "Profits", "Reports",
				"Settings");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionLogout();

	}

}
