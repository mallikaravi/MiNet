package com.novare.minet.action;

import com.novare.minet.controller.ManagerController;
import com.novare.minet.model.User;
import com.novare.minet.service.IManagerService;
import com.novare.minet.serviceimpl.ManagerServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.ManagerView;

public class ManagerMenuAction extends MiNetMenuAction {

	public ManagerMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "Manager Menu options:";
		ManagerView view = new ManagerView(getAppHeader(), title);
		IManagerService model = new ManagerServiceImpl();
		ManagerController controller = new ManagerController(model, view);
		controller.setMenuVisible(context == MenuContext.MANAGER);
		controller.requestUserInput(context, currentUser);
	}

}
