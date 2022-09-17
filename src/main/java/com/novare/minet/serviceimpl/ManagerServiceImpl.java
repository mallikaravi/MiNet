package com.novare.minet.serviceimpl;

import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.Inventory;
import com.novare.minet.model.Order;
import com.novare.minet.model.Transaction;
import com.novare.minet.model.User;
import com.novare.minet.service.IManagerService;
import com.novare.minet.util.MenuContext;

public class ManagerServiceImpl extends BaseServiceImpl implements IManagerService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
			case 1 -> {
				new WelcomeMenuAction(MenuContext.VIEWORDERLIST, currentUser);
			}
			case 2 -> {
				new WelcomeMenuAction(MenuContext.REVIEWINVENTORY, currentUser);
			}
			case 3 -> {
				new WelcomeMenuAction(MenuContext.SAVETRANSACTIONS, currentUser);
			}
			default -> throw new IndexOutOfBoundsException();

		}
	}

	@Override
	public Order vieworderList(Order order) {
		return null;
	}

	@Override
	public Inventory reviewInventory(Inventory inventory) {
		return null;
	}

	@Override
	public Transaction saveTransactions(Transaction transaction) {
		return null;
	}

}
