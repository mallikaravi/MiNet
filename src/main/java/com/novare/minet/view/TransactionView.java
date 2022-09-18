package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class TransactionView extends BaseView {

	public TransactionView(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Start", "Return",  "List", "Search");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

	public int askSearchWithNumber() {
		printMessage("Enter Search Transaction ID: ");
		return getUserInput();
	}

	public String askSearchProdWithNameOrId() {
		printMessage("Enter Product (ID | Full Name | Short Name): ");
		return getUserText();
	}

	public int askForChooseProduct(List<?> items) {
		printMessage("Choose the product: ");
		setMenuOptionsInRow(items);
		return getSelectedOptionFromMenu(items.size()) - 1;
	}

	public Integer askProductQty() {
		printMessage("Product Quantity: ");
		return getUserInput();
	}

	public boolean askMoreSale() {
		printMessage("Continue transaction [Yes/No]:");
		return askConfirmationYesOrNo();
	}
}
