package com.novare.minet.action;

import com.novare.minet.controller.SupplierController;
import com.novare.minet.model.User;
import com.novare.minet.service.ISupplierService;
import com.novare.minet.serviceimpl.SupplierServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.SupplierView;

public class SuppliersMenuAction extends BaseMenuAction {

	public SuppliersMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case CREATE_SUPPLIER -> title ="Create Suppler Option :";
		case EDIT_SUPPLIER -> title = "Edit Supplier Option :";
		case DELETE_SUPPLIER -> title ="Delete Supplier Option:";
		case SUPPLIER_LIST -> title ="View Suppliers List  :";
		default -> title="Suplier Menu Options:";

		}
		SupplierView view = new SupplierView(title);
		ISupplierService model = new SupplierServiceImpl();
		SupplierController controller = new SupplierController(model, view);
		controller.setMenuVisible(context == MenuContext.SUPPLIERS);
		controller.requestUserInput(context, currentUser);

	}

}
