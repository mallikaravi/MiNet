package com.novare.minet.serviceimpl;

import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.ICashierService;
import com.novare.minet.util.MenuContext;

public class CashierServiceImpl extends BaseServiceImpl implements ICashierService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 1 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
		}
		case 2 -> {
			new CashierMenuAction(MenuContext.START_TRANSACTION, currentUser);
		}
		case 3 -> {
			new CashierMenuAction(MenuContext.PRODUCT, currentUser);

		}
		case 4 -> {
			new CashierMenuAction(MenuContext.RETURN_PRODUCT, currentUser);

		}
		case 5 -> {
			new CashierMenuAction(MenuContext.INVENTORY, currentUser);

		}
		case 6 -> {
			new CashierMenuAction(MenuContext.TRANSACTION_LIST, currentUser);

		}
		default -> throw new IndexOutOfBoundsException();
		}

	}

}
