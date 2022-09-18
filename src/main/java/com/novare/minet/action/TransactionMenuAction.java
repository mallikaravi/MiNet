package com.novare.minet.action;

import com.novare.minet.controller.TransactionController;
import com.novare.minet.model.User;
import com.novare.minet.service.ITransactionService;
import com.novare.minet.serviceimpl.TransactionServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.TransactionView;

public class TransactionMenuAction extends BaseMenuAction {

	public TransactionMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case CREATE -> title = "Start Transaction Option :";
		case RETURN_PRODUCT -> title = "Return Product option : :";
		case LIST -> title = "Transaction List Option :";
		case SEARCH -> title = "Search Transaction Option :";
		case EDIT->title = "Edit Transaction Option :";
		default -> title = "Transaction Menu options";

		}
		TransactionView view = new TransactionView(title);
		ITransactionService model = new TransactionServiceImpl();
		TransactionController controller = new TransactionController(model, view);
		controller.setMenuVisible(context == MenuContext.TRANSACTION);
		controller.requestUserInput(context, currentUser);

	}
}
