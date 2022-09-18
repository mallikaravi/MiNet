package com.novare.minet.serviceimpl;

import com.novare.minet.action.InventoryMenuAction;
import com.novare.minet.action.OrderMenuAction;
import com.novare.minet.action.ProductMenuAction;
import com.novare.minet.action.TransactionMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.ICashierService;
import com.novare.minet.util.MenuContext;

public class CashierServiceImpl extends BaseServiceImpl implements ICashierService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 0 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
		}
		case 1 -> {
			new TransactionMenuAction(MenuContext.TRANSACTION, currentUser);
		}
		case 2 -> {
			new OrderMenuAction(MenuContext.ORDER, currentUser);
		}
		case 3 -> {
			new InventoryMenuAction(MenuContext.INVENTORY, currentUser);
		}
		case 4 -> {
			new ProductMenuAction(MenuContext.PRODUCT, currentUser);
		}
		default -> throw new IndexOutOfBoundsException();
		}
	}
}
