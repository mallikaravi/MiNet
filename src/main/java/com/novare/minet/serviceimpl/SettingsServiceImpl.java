package com.novare.minet.serviceimpl;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.SettingsMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.ISettingsService;
import com.novare.minet.util.MenuContext;

public class SettingsServiceImpl extends MiNetServiceImpl implements ISettingsService {

	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 0 -> {
			switch (currentUser.getRole()) {
			case ADMIN -> new AdminMenuAction(MenuContext.ADMIN, currentUser);
			case MANAGER -> new ManagerMenuAction(MenuContext.MANAGER, currentUser);
			case CASHIER -> new CashierMenuAction(MenuContext.CASHIER, currentUser);
			default -> new WelcomeMenuAction(MenuContext.WELCOME, null);
			}
		}
		case 1 -> {
			new SettingsMenuAction(MenuContext.UPDATE_USER, currentUser);
		}
		case 2 -> {
			new SettingsMenuAction(MenuContext.CHANGE_PASSWORD, currentUser);
		}
		case 3 -> {
			new SettingsMenuAction(MenuContext.DELETE_USER, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}
	}

}
