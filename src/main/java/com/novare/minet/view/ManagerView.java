package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class ManagerView extends BaseView {

	public ManagerView(String title) {
		super(title);
	}

	public ManagerView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Transaction", "Products", "Suppliers", "Order", "Settings");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionLogout();
	}

}
