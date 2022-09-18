package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class ProductView extends BaseView {

	public ProductView(String title) {
		super(title);
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
		printMessage("Product Full Name: ");
		return getUserText();
	}

	public String askProductShortName() {
		printMessage("Product Short Name: ");
		return getUserText();
	}

	public String askProductDescription() {
		printMessage("Product Description: ");
		return getUserText();
	}

	public Integer askProductMinQty() {
		printMessage("Product Minimum Quantity: ");
		return getUserInput();
	}

	public Double askProductSellingPrice() {
		printMessage("Selling Price: ");
		return getUserInputDouble();
	}

	public Double askProductCostPrice() {
		printMessage("Purchase Price: ");
		return getUserInputDouble();
	}

	public int askDefaultSupplier(List<?> allSuppliers) {
		printMessage("Select the Default Supplier: ");
		setMenuOptionsInRow(allSuppliers);
		return getSelectedOptionFromMenu(allSuppliers.size()) - 1;
	}

}
