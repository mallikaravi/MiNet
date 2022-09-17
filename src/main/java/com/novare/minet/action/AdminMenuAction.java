package com.novare.minet.action;

import com.novare.minet.controller.AdminController;
import com.novare.minet.model.User;
import com.novare.minet.service.IAdminService;
import com.novare.minet.serviceimpl.AdminServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.AdminView;

public class AdminMenuAction extends BaseMenuAction {

	public AdminMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
			case PAYMENTS -> title = " View Payments :";
			case CASHFLOW -> title = "  View CashFlow :";
			case PROFITS -> title = " View Profits :";
			case REVIEWTRANSACTIONS -> title = "Transaction options :";
		}
		AdminView view = new AdminView(title);
		IAdminService model = new AdminServiceImpl();
		AdminController controller = new AdminController(model, view);
		controller.setMenuVisible(context == MenuContext.ADMIN);
		controller.requestUserInput(context, currentUser);

	}
}
