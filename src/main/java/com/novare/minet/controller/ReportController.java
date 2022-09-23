package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.IReportService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.ReportView;

public class ReportController extends MiNetController {

	public ReportController(IReportService model, ReportView view) {
		super(model, view);
	}

	@Override
	public IReportService getModel() {
		return (IReportService) super.getModel();
	}

	@Override
	public ReportView getView() {
		return (ReportView) super.getView();
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			getView().waitForDecision();
//			switch (context) {
//			case BEST_SELLING_PRODUCTS -> bestSellingProducts();
//			case PRODUCT_PROFITS -> productProfits();
//			default -> {
//				selectedOption = getView().getUserInput();
//			}
//			}
			getModel().handleOption(selectedOption, getUserSession());
		} catch (Exception e) {
			getView().printInvalidOption();
			getView().printUserRequest();
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		}
	}

}
