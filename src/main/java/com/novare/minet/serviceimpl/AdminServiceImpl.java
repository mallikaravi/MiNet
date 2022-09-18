package com.novare.minet.serviceimpl;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.InventoryMenuAction;
import com.novare.minet.action.OrderMenuAction;
import com.novare.minet.action.ProductMenuAction;
import com.novare.minet.action.ReportsMenuAction;
import com.novare.minet.action.SuppliersMenuAction;
import com.novare.minet.action.TransactionMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IAdminService;
import com.novare.minet.util.MenuContext;

public class AdminServiceImpl extends BaseServiceImpl implements IAdminService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		
		switch (selectedOption) {
		case 0 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
		}
		
		case 1 -> {
			new InventoryMenuAction(MenuContext.INVENTORY, currentUser);
		}
		case 2 -> {
			new TransactionMenuAction(MenuContext.TRANSACTION, currentUser);

		}
		case 3 -> {
			new ProductMenuAction(MenuContext.PRODUCT, currentUser);

		}
		case 4 -> {
			new SuppliersMenuAction(MenuContext.SUPPLIERS, currentUser);

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
		case 8-> {
			new OrderMenuAction(MenuContext.ORDER, currentUser);

		}
		case 9 -> {
			new ReportsMenuAction(MenuContext.REPORTS, currentUser);

		}
		default -> throw new IndexOutOfBoundsException();
		}

	}

}
