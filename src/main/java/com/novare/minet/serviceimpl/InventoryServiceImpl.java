package com.novare.minet.serviceimpl;

import com.novare.minet.action.InventoryMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IInventoryService;
import com.novare.minet.util.MenuContext;

public class InventoryServiceImpl extends BaseServiceImpl implements IInventoryService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 1 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
		}
		case 2 -> {
			new InventoryMenuAction(MenuContext.INVENTORY_LIST, currentUser);
		}
		case 3 -> {
			new InventoryMenuAction(MenuContext.CREATE_ORDER, currentUser);

		}
		case 4 -> {
			new InventoryMenuAction(MenuContext.ORDER_LIST, currentUser);

		}
		case 5 -> {
			new InventoryMenuAction(MenuContext.PENDING_ORDERS, currentUser);

		}
		case 6 -> {
			new InventoryMenuAction(MenuContext.SEARCH_ORDER, currentUser);

		}
		default -> throw new IndexOutOfBoundsException();
		}

	

	}

}
