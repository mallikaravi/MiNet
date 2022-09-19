package com.novare.minet.serviceimpl;

import com.novare.minet.action.ProductMenuAction;
import com.novare.minet.action.SuppliersMenuAction;
import com.novare.minet.action.TransactionMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IManagerService;
import com.novare.minet.util.MenuContext;

public class ManagerServiceImpl extends MiNetServiceImpl implements IManagerService {

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
			new SuppliersMenuAction(MenuContext.SUPPLIERS, currentUser);
		}
		default -> throw new IndexOutOfBoundsException();

		}
	}

}
