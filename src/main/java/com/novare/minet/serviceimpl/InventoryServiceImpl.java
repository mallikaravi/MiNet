package com.novare.minet.serviceimpl;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.InventoryMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IInventoryService;
import com.novare.minet.util.MenuContext;

public class InventoryServiceImpl extends BaseServiceImpl implements IInventoryService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 0 -> {
			switch (currentUser.getRole()) {
			case ADMIN -> new AdminMenuAction(MenuContext.ADMIN, currentUser);
			case MANAGER -> new ManagerMenuAction(MenuContext.MANAGER, currentUser);
			case CASHIER -> new CashierMenuAction(MenuContext.CASHIER, currentUser);
			default -> new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
			}
		}
		case 1 -> {
			new InventoryMenuAction(MenuContext.LIST, currentUser);
		}
//		case 2 -> {
//			new InventoryMenuAction(MenuContext.CREATE, currentUser);
//		}
		case 2 -> {
			new InventoryMenuAction(MenuContext.EDIT, currentUser);
		}
		case 3 -> {
			new InventoryMenuAction(MenuContext.DELETE, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}

	}


}
