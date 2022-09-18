package com.novare.minet.serviceimpl;

import com.novare.minet.action.InventoryMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.ProductMenuAction;
import com.novare.minet.action.SuppliersMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IManagerService;
import com.novare.minet.util.MenuContext;

public class ManagerServiceImpl extends BaseServiceImpl implements IManagerService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
			case 1 -> {
				new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
			}
			case 2 -> {
				new ManagerMenuAction(MenuContext.TRANSACTION_LIST, currentUser);
			}
			case 3 -> {
				new InventoryMenuAction(MenuContext.INVENTORY, currentUser);
			}
			case 4 -> {
				new ProductMenuAction(MenuContext.PRODUCT, currentUser);
			}
			case 5 -> {
				new SuppliersMenuAction(MenuContext.SUPPLIERS, currentUser);
			}
			default -> throw new IndexOutOfBoundsException();

		}
	}

	
}
