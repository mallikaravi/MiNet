package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.ICashierService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.CashierView;

public class CashierController extends BaseController {

	public CashierController(ICashierService model, CashierView view) {
		super(model, view);
	}

	@Override
	public ICashierService getModel() {
		return (ICashierService) super.getModel();
	}

	@Override
	public CashierView getView() {
		return (CashierView) super.getView();
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case START_TRANSACTION -> viewTransaction();
			case RETURN_PRODUCT -> returnProduct();
			case TRANSACTION_LIST -> viewTransactionList();
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

	private Object viewTransactionList() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object returnProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object viewTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

}
