package com.novare.minet.action;

import com.novare.minet.controller.InventoryController;
import com.novare.minet.model.User;
import com.novare.minet.service.IInventoryService;
import com.novare.minet.serviceimpl.InventoryServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.InventoryView;

public class InventoryMenuAction extends BaseMenuAction {

	public InventoryMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";

		switch (context) {
		case CREATE -> title = " Create Inventory Option:";
		case EDIT -> title = " View Inventory Option:";
		case DELETE -> title = "Create Inventory Option :";
		case LIST -> title = "List of Inventory Option :";
		default -> title = "Inventory Option:";
		}
		InventoryView view = new InventoryView(title);
		IInventoryService model = new InventoryServiceImpl();
		InventoryController controller = new InventoryController(model, view);
		controller.setMenuVisible(context == MenuContext.INVENTORY);
		controller.requestUserInput(context, currentUser);
	}
}
