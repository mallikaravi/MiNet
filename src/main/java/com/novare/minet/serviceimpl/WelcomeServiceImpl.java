package com.novare.minet.serviceimpl;

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
//			new MainMenuAction(MenuContext.MAIN, currentUser);
		}
		case 4 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, null);
		}

		default -> throw new IndexOutOfBoundsException();
		}
	}
}