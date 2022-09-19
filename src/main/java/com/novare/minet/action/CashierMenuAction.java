package com.novare.minet.action;

import com.novare.minet.controller.CashierController;
import com.novare.minet.model.User;
import com.novare.minet.service.ICashierService;
import com.novare.minet.serviceimpl.CashierServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.CashierView;

public class CashierMenuAction extends MiNetMenuAction {

	public CashierMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String menuTitle = "Cashier Menu Options:";
		CashierView view = new CashierView(getAppHeader(), menuTitle);
		ICashierService model = new CashierServiceImpl();
		CashierController controller = new CashierController(model, view);
		controller.setMenuVisible(context == MenuContext.CASHIER);
		controller.requestUserInput(context, currentUser);
	}

}
