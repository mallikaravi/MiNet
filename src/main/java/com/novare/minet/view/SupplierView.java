package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class SupplierView extends BaseView{

	public SupplierView(String title) {
		super(title);
	}
	@Override
	public List<String> getMenuOptions() {
		return List.of("Create Supply, Edit Supply, Delete Supply, Supply List");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}
}
