package com.novare.minet.action;

import com.novare.minet.controller.ManagerController;
import com.novare.minet.model.User;
import com.novare.minet.service.IManagerService;
import com.novare.minet.serviceimpl.ManagerServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.ManagerView;

public class ManagerMenuAction extends BaseMenuAction {

	public ManagerMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
			case VIEWORDERLIST -> title = " View Order List :";
			case REVIEWINVENTORY -> title = "  Review inventory option :";
			case SAVETRANSACTIONS -> title = " Save  Transactions option :";

		}
		ManagerView view = new ManagerView(title);
		IManagerService model = new ManagerServiceImpl();
		ManagerController controller = new ManagerController(model, view);
		controller.setMenuVisible(context == MenuContext.MANAGER);
		controller.requestUserInput(context, currentUser);
	}

}
