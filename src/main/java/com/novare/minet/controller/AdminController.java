package com.novare.minet.controller;

import java.util.EnumSet;

import com.novare.minet.model.User;
import com.novare.minet.service.IAdminService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.AdminView;

public class AdminController extends MiNetController {

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
			if (EnumSet.of(MenuContext.PAYMENTS, MenuContext.CASHFLOW, MenuContext.PROFITS).contains(context)) {
				getView().printInprogress();
				getView().waitForDecision();
			}

			selectedOption = getView().getUserInput();
			getModel().handleOption(selectedOption, getUserSession());
		} catch (Exception e) {
			getView().printInvalidOption();
			getView().printUserRequest();
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		}
	}

}
