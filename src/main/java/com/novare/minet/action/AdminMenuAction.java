package com.novare.minet.action;

import com.novare.minet.controller.AdminController;
import com.novare.minet.model.User;
import com.novare.minet.service.IAdminService;
import com.novare.minet.serviceimpl.AdminServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.AdminView;

public class AdminMenuAction extends MiNetMenuAction {

	public AdminMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "Admin Menu Options:";
		AdminView view = new AdminView(getAppHeader(),title);
		IAdminService model = new AdminServiceImpl();
		AdminController controller = new AdminController(model, view);
		controller.setMenuVisible(context == MenuContext.ADMIN);
		controller.requestUserInput(context, currentUser);

	}
}
