package com.novare.minet.serviceimpl;

import com.novare.minet.action.ReportsMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IReportService;
import com.novare.minet.util.MenuContext;

public class ReportServiceImpl extends BaseServiceImpl implements IReportService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
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
