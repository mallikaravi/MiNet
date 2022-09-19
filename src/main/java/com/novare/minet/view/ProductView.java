package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class ProductView extends BaseView {

	public ProductView(String title) {
		super(title);
	}

	public ProductView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("List", "Create", "Edit", "Delete", "Search");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

	public String askProductFullName() {
		return getUserText("Full Name: ");
	}

	public String askProductShortName() {
		return getUserText("Short Name: ");
	}

	public String askProductDescription() {
		return getUserText("Description: ");
	}

	public Integer askProductMinQty() {
		return getUserInput("Minimum Quantity: ");
	}

	public Double askProductSellingPrice() {
		return getUserInputDouble("Selling Price: ");
	}

	public Double askProductCostPrice() {
		return getUserInputDouble("Purchase Price: ");
	}

	public int askDefaultSupplier(List<?> allSuppliers) {
		printMessage("Select the Default Supplier: ");
		setMenuOptionsInRow(allSuppliers);
		return getSelectedOptionFromMenu(allSuppliers.size()) - 1;
	}

	public Double askProductAvailQty() {
		return getUserInputDouble("Available Quantity: ");
	}

}
