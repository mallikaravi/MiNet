package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class TransactionView extends BaseView {

	public TransactionView(String title) {
		super(title);
	}

	public TransactionView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Start", "Return", "List", "Search");
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

	public int askForChooseProduct(String printProductDetails, List<?> items) {
		printMessage("Products hit: \n");
		printMessage(printProductDetails);
		return getSelectedOptionFromMenu(items.size()) - 1;
	}

	public Integer askProductQty() {
		return getUserInput("Product Quantity: ");
	}

	public boolean askMoreSale() {
		printMessage("Continue transaction [Yes/No]:");
		return askConfirmationYesOrNo();
	}
}
