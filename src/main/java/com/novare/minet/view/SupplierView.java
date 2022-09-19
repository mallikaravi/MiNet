package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class SupplierView extends BaseView {

	public SupplierView(String title) {
		super(title);
	}

	public SupplierView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("List", "Create", "Edit", "Delete", "Search");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionLogout();

	}

	public String askSupplierName() {
		return getUserText("Name: ");
	}

	public String askSupplierAddress() {
		return getUserText("Address: ");
	}

	public String askSupplierEmail() {
		return getUserText("Email: ");
	}

	public String askSupplierPhoneNumber() {
		return getUserText("Phone Number: ");
	}
}
