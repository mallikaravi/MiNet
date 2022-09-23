package com.novare.minet.view;

import java.util.List;

import com.novare.minet.util.PrintHandler;

public class ReportView extends MinetView {

	public ReportView(String title) {
		super(title);
	}

	public ReportView(String appHeader, String title) {
		super(appHeader, title);
	}

	@Override
	public List<String> getMenuOptions() {
		return List.of("Best Selling Products","Product profits");
	}

	@Override
	public void printNavigationMenu() {
		PrintHandler.optionBackToMainMenu();

	}
}
