package com.novare.minet.action;

import com.novare.minet.controller.ReportController;
import com.novare.minet.model.User;
import com.novare.minet.service.IReportService;
import com.novare.minet.serviceimpl.ReportServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.ReportView;

public class ReportsMenuAction  extends MiNetMenuAction{

	public ReportsMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case BEST_SELLING_PRODUCTS -> title ="View Best Selling Products Option:";
		case PRODUCT_PROFITS -> title ="Product Profits Option :";
		default -> title ="Product Report Menu options";

		}
		ReportView view = new ReportView(getAppHeader(),title);
		IReportService model = new ReportServiceImpl();
		ReportController controller = new ReportController(model, view);
		controller.setMenuVisible(context == MenuContext.INVENTORY);
		controller.requestUserInput(context, currentUser);
	
	}

}
