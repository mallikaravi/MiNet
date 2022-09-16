package com.novare.minet.serviceimpl;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IWelcomeService;
import com.novare.minet.util.MenuContext;

public class WelcomeServiceImpl extends BaseServiceImpl implements IWelcomeService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 0 -> {
			System.exit(0);
		}
		case 1 -> {
			new WelcomeMenuAction(MenuContext.SIGNUP, currentUser);
		}
		case 2 -> {
			new WelcomeMenuAction(MenuContext.LOGIN, currentUser);
		}
		case 3 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, null);
		}
		case 4 -> {
			new AdminMenuAction(MenuContext.ADMIN, currentUser);
		}
		case 5 -> {
			new AdminMenuAction(MenuContext.MANAGER, currentUser);
		}
		case 6 -> {
			new AdminMenuAction(MenuContext.CASHIER, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}
	}
}