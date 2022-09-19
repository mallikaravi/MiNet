package com.novare.minet.serviceimpl;

import com.novare.minet.action.OrderMenuAction;
import com.novare.minet.action.ProductMenuAction;
import com.novare.minet.action.SettingsMenuAction;
import com.novare.minet.action.TransactionMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.ICashierService;
import com.novare.minet.util.MenuContext;

public class CashierServiceImpl extends MiNetServiceImpl implements ICashierService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 0 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, null);
		}
		case 1 -> {
			new TransactionMenuAction(MenuContext.TRANSACTION, currentUser);
		}
		case 2 -> {
			new ProductMenuAction(MenuContext.PRODUCT, currentUser);
		}
		case 3 -> {
			new OrderMenuAction(MenuContext.ORDER, currentUser);
		}
		case 4 -> {
			new SettingsMenuAction(MenuContext.SETTINGS, currentUser);

		}
		default -> throw new IndexOutOfBoundsException();
		}
	}
}
