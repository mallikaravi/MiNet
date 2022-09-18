package com.novare.minet.view;

import java.util.List;

import com.novare.minet.model.Product;
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

	public int askForEdit(List<?> productList) {
		printMessage("Select the product for edit: ");
		setMenuOptionsInRow(productList);
		int selection = getSelectedOptionFromMenu(productList.size()) - 1;
		printMessage("Do you want to edit " + productList.get(selection) + " [Yes/No]: ");
		return askConfirmationYesOrNo() ? selection : -1;

	}

	public int askForDelete(List<Product> productList) {
		printMessage("Select the product for delete: ");
		setMenuOptionsInRow(productList);
		int selection = getSelectedOptionFromMenu(productList.size()) - 1;
		printMessage("Do you want to delete " + productList.get(selection) + " [Yes/No]: ");
		return askConfirmationYesOrNo() ? selection : -1;

	}

}
