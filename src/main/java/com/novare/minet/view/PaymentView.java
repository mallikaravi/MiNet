package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class PaymentView extends BaseView {

	public PaymentView(String menuTitle) {
		super(menuTitle);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Implementation is in progress");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();
	}

}
