package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.ITransactionService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.BaseView;
import com.novare.minet.view.TransactionView;

public class TransactionController extends BaseController {

	public TransactionController(ITransactionService model, BaseView view) {
		super(model, view);
	}

	@Override
	public ITransactionService getModel() {
		return (ITransactionService) super.getModel();
	}

	@Override
	public TransactionView getView() {
		return (TransactionView) super.getView();
	}

	@Override
	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case CREATE -> createTransaction();
			case RETURN_PRODUCT -> performReturn();
			case SEARCH -> performSearch();
			case LIST -> displayTransactions();
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

	private Object displayTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object performSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object performReturn() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object createTransaction() {
		// TODO Auto-generated method stub
		return null;
	}
}
