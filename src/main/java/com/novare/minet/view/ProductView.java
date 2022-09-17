package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class ProductView extends BaseView {

	public ProductView(String title) {
		super(title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Create Product", "List Of products", "Edit Product", "Delete product","Search product");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}

}
