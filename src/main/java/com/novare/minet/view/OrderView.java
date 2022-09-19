package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class OrderView extends BaseView {

	public OrderView(String title) {
		super(title);
	}

	public OrderView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("List", "Create", "Delete", "Waiting", "Pending", "Search");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

	public Double askForOrderQuantity() {
		return getUserInputDouble("Enter Order Quantity: ");
	}

	public int askOrderSelectionToApprove(String table, List<?> items) {
		printMessage(table);
		int selection = getSelectedOptionFromMenu(items.size()) - 1;
		return selection;
	}

	public int askOrderSelectionToReceive(String table, List<?> items) {
		printMessage(table);
		int selection = getSelectedOptionFromMenu(items.size()) - 1;
		return selection;
	}

	public boolean askForAprrove() {
		return askConfirmationYesOrNo("Do you want to approve [Yes/No]: ");
	}

	public boolean askForClose() {
		return askConfirmationYesOrNo("Do you want to close the order [Yes/No]: ");
	}

}
