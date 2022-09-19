package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class AdminView extends BaseView {

	public AdminView(String title) {
		super(title);
	}

	public AdminView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Transaction", "Products", "Suppliers", "Payments", "Cash Flow", "Profits", "Order", "Reports","Settings");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionLogout();

	}

}
