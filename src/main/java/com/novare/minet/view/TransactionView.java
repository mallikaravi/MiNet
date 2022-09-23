package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class TransactionView extends MinetView {

	public TransactionView(String title) {
		super(title);
	}

	public TransactionView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("List", "Start", "Return", "Search");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

	public int askSearchWithNumber() {
		return getUserInput("Enter Search Transaction ID: ");
	}

	public String askSearchProdWithNameOrId() {
		return getUserText("Enter Product (ID | Full Name | Short Name): ");
	}

	public Integer askProductQty() {
		return getUserInput("Product Quantity: ");
	}

	public boolean askMoreSale() {
		return askConfirmationYesOrNo("Continue transaction [Yes/No]:");
	}

	public void printReceipt(String printReceipt) {
		printMessage(printReceipt);
	}

	
}
