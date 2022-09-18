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
			case INVENTORY -> viewInventories();

			case PRODUCT -> viewProduct();

			case TRANSACTION -> TransactionList();

			case SUPPLIERS -> viewSuppliers();

			case PAYMENTS -> viewPayments();

			case CASHFLOW -> viewCashFlow();

			case PROFITS -> viewProfits();

			case ORDER_HISTORY -> orderHistory();

			case REPORTS -> viewReports();

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

	private Object viewReports() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object orderHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object viewProfits() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object viewCashFlow() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object viewPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object viewSuppliers() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object TransactionList() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object viewProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object viewInventories() {
		// TODO Auto-generated method stub
		return null;
	}

}
