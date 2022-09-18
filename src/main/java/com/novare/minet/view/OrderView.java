package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class OrderView extends BaseView {

	public OrderView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("List", "Create", "Received", "Delete", "Pending", "Search", "History");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

	public Double askForOrderQuantity() {
		printMessage("Enter Order Quantity: ");
		return getUserInputDouble();
	}

	public int askOrderSelectionToApprove(List<?> items) {
		printMessage("Select the item for approve: ");
		setMenuOptionsInRow(items);
		int selection = getSelectedOptionFromMenu(items.size()) - 1;
		return selection;
	}

	public int askOrderSelectionToReceive(List<?> items) {
		printMessage("Select the item that received: ");
		setMenuOptionsInRow(items);
		int selection = getSelectedOptionFromMenu(items.size()) - 1;
		return selection;
	}

	public boolean askForAprrove() {
		printMessage("Do you want to approve [Yes/No]: ");
		return askConfirmationYesOrNo();
	}

	public boolean askForClose() {
		printMessage("Do you want to close the order [Yes/No]: ");
		return askConfirmationYesOrNo();
	}

}
