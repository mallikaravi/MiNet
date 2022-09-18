package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class SupplierView extends BaseView {

	public SupplierView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Create", "Edit", "Delete", "List", "Search");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionLogout();

	}

	public String askSupplierName() {
		printMessage("Supplier Name: ");
		return getUserText();
	}

	public String askSupplierAddress() {
		printMessage("Supplier Address: ");
		return getUserText();
	}

	public String askSupplierEmail() {
		printMessage("Supplier Email: ");
		return getUserText();
	}

	public String askSupplierPhoneNumber() {
		printMessage("Supplier phoneNumber: ");
		return getUserText();
	}
}
