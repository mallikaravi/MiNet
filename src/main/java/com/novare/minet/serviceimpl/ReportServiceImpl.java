package com.novare.minet.serviceimpl;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.ReportsMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IReportService;
import com.novare.minet.util.MenuContext;

public class ReportServiceImpl extends BaseServiceImpl implements IReportService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 0 -> {
			switch (currentUser.getRole()) {
			case ADMIN -> new AdminMenuAction(MenuContext.ADMIN, currentUser);
			case MANAGER -> new ManagerMenuAction(MenuContext.MANAGER, currentUser);
			case CASHIER -> new CashierMenuAction(MenuContext.CASHIER, currentUser);
			default -> new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
			}
		}
		case 1 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
		}
		case 2 -> {
			new ReportsMenuAction(MenuContext.BEST_SELLING_PRODUCTS, currentUser);
		}
		case 3 -> {
			new ReportsMenuAction(MenuContext.PRODUCT_PROFITS, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();

		}
	}

}
