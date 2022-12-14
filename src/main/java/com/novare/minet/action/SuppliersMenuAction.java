package com.novare.minet.action;

import com.novare.minet.controller.SupplierController;
import com.novare.minet.model.User;
import com.novare.minet.service.ISupplierService;
import com.novare.minet.serviceimpl.SupplierServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.SupplierView;

public class SuppliersMenuAction extends MiNetMenuAction {

	public SuppliersMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case CREATE -> title = "Create Suppler Options:";
		case EDIT -> title = "Edit Supplier Options:";
		case DELETE -> title = "Delete Supplier Options:";
		case LIST -> title = "Suppliers List Options:";
		case SEARCH -> title = "Search Supplier Options:";
		default -> title = "Supplier Menu Options:";
		}
		SupplierView view = new SupplierView(getAppHeader(), title);
		ISupplierService model = new SupplierServiceImpl();
		SupplierController controller = new SupplierController(model, view);
		controller.setMenuVisible(context == MenuContext.SUPPLIERS);
		controller.requestUserInput(context, currentUser);

	}

}
