package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.IAdminService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.AdminView;

public class AdminController extends BaseController {

	public AdminController(IAdminService model, AdminView view) {
		super(model, view);
	}

	@Override
	public IAdminService getModel() {
		return (IAdminService) super.getModel();
	}

	@Override
	public AdminView getView() {
		return (AdminView) super.getView();
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
				case PAYMENTS -> viewPAYMENTS();
				
				case CASHFLOW -> viewCashFlow();

				case PROFITS -> viewPAYMENTS();

				case REVIEWTRANSACTIONS -> reviewTransactions();

				default -> {
					selectedOption = getView().getUserInput();
				}
			}
			getModel().handleOption(selectedOption, getUserSession());
		} catch (Exception e) {
			getView().printInvalidOption();
			getView().printUserRequest();
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		}
	}

	private Object reviewTransactions() {
		return null;
	}

	private Object viewCashFlow() {
		return null;
	}

	private Object viewPAYMENTS() {
		return null;
	}
}
