package com.novare.minet.serviceimpl;

import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.Transaction;
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
				new WelcomeMenuAction(MenuContext.PAYMENTS, currentUser);
			}
			case 3 -> {
				new WelcomeMenuAction(MenuContext.REVIEWTRANSACTIONS, currentUser);

			}
			case 4 -> {
				new WelcomeMenuAction(MenuContext.CASHFLOW, currentUser);

			}
			case 5 -> {
				new WelcomeMenuAction(MenuContext.PROFITS, currentUser);

			}
			default -> throw new IndexOutOfBoundsException();
		}

	}

	@Override
	public Transaction reviewTransactions(Transaction transaction) {
		return null;
	}

}
