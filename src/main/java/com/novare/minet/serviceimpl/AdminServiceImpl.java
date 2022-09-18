package com.novare.minet.serviceimpl;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IAdminService;
import com.novare.minet.util.MenuContext;

public class AdminServiceImpl extends BaseServiceImpl implements IAdminService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		
		switch (selectedOption) {
		case 1 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
		}
		case 2 -> {
			new AdminMenuAction(MenuContext.INVENTORY, currentUser);
		}
		case 3 -> {
			new AdminMenuAction(MenuContext.TRANSACTION_LIST, currentUser);

		}
		case 4 -> {
			new AdminMenuAction(MenuContext.SUPPLIERS, currentUser);

		}
		case 5 -> {
			new AdminMenuAction(MenuContext.PAYMENTS, currentUser);

		}
		case 6 -> {
			new AdminMenuAction(MenuContext.CASHFLOW, currentUser);

		}
		case 7 -> {
			new AdminMenuAction(MenuContext.PROFITS, currentUser);

		}
		case 8 -> {
			new AdminMenuAction(MenuContext.ORDER_HISTORY, currentUser);

		}
		case 9 -> {
			new AdminMenuAction(MenuContext.REPORTS, currentUser);

		}
		default -> throw new IndexOutOfBoundsException();
		}

	}

}
