package com.novare.minet.action;

import com.novare.minet.controller.TransactionController;
import com.novare.minet.model.User;
import com.novare.minet.service.ITransactionService;
import com.novare.minet.serviceimpl.TransactionServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.TransactionView;

public class TransactionMenuAction extends MiNetMenuAction {

	public TransactionMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case CREATE -> title = "Start Transaction Options:";
		case RETURN_PRODUCT -> title = "Return Product Options:";
		case LIST -> title = "Transaction List Options:";
		case SEARCH -> title = "Search Transaction Options:";
		case EDIT->title = "Edit Transaction Options:";
		default -> title = "Transaction Menu Options:";

		}
		TransactionView view = new TransactionView(getAppHeader(),title);
		ITransactionService model = new TransactionServiceImpl();
		TransactionController controller = new TransactionController(model, view);
		controller.setMenuVisible(context == MenuContext.TRANSACTION);
		controller.requestUserInput(context, currentUser);

	}
}
