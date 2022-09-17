package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.ISupplierService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.SupplierView;

public class SupplierController extends BaseController {

	public SupplierController(ISupplierService model, SupplierView view) {
		super(model, view);
	}

	@Override
	public ISupplierService getModel() {
		return (ISupplierService) super.getModel();
	}

	@Override
	public SupplierView getView() {
		return (SupplierView) super.getView();
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case CREATE_SUPPLY -> createSupply();

			case EDIT_SUPPLY -> editSupply();

			case DELETE_SUPPLY -> deleteSupply();

			case SUPPLY_LIST -> supplyList();
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

	private Object supplyList() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object deleteSupply() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object editSupply() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object createSupply() {
		// TODO Auto-generated method stub
		return null;
	}
}
