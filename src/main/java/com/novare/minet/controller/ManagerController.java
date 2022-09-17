package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.IManagerService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.ManagerView;

public class ManagerController extends BaseController {

	public ManagerController(IManagerService model, ManagerView view) {
		super(model, view);
	}

	@Override
	public IManagerService getModel() {
		return (IManagerService) super.getModel();
	}

	@Override
	public ManagerView getView() {
		return (ManagerView) super.getView();
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case INVENTORY -> ViewInventory();

			case TRANSACTION_LIST -> TransactionList();

			case PRODUCT -> ViewProduct();

			case SUPPLIERS -> ViewSuppliers();

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

	private Object ViewSuppliers() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object ViewProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object TransactionList() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object ViewInventory() {
		// TODO Auto-generated method stub
		return null;
	}

}
