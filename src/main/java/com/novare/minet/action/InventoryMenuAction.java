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
		case INVENTORY_LIST -> title = " View Inventory :";
		case CREATE_ORDER -> title = " Create Order Option :";
		case ORDER_LIST -> title = "LIst of Orders Option :";
		case PENDING_ORDERS -> title = "Pending order Option:";
		case SEARCH_ORDER -> title = "Search Order Option :";
		default -> title="Inventory Option:";
		}
		InventoryView view = new InventoryView(title);
		IInventoryService model = new InventoryServiceImpl();
		InventoryController controller = new InventoryController(model, view);
		controller.setMenuVisible(context == MenuContext.INVENTORY);
		controller.requestUserInput(context, currentUser);
	}
}
