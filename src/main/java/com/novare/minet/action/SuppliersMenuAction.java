package com.novare.minet.action;

import com.novare.minet.controller.SupplierController;
import com.novare.minet.model.User;
import com.novare.minet.service.ISupplierService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.SupplierView;

public class SuppliersMenuAction extends BaseMenuAction {

	public SuppliersMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case CREATE_SUPPLY -> title = "Create Supply Option :";
		case EDIT_SUPPLY -> title = "Edit Supply Option :";
		case DELETE_SUPPLY -> title = "Delete Supply Option:";
		case SUPPLY_LIST -> title = " View Suppliers List  :";

		}
		SupplierView view = new SupplierView(title);
		ISupplierService model = new SupplierServiceImpl();
		SupplierController controller = new SupplierController(model, view);
		controller.setMenuVisible(context == MenuContext.SUPPLIERS);
		controller.requestUserInput(context, currentUser);

	}

}
