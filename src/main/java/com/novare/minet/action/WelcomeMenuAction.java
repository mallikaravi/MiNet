package com.novare.minet.action;

import com.novare.minet.controller.WelcomeController;
import com.novare.minet.model.User;
import com.novare.minet.service.IUserService;
import com.novare.minet.serviceimpl.UserServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.WelcomeView;

public class WelcomeMenuAction extends BaseMenuAction {

	public WelcomeMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		WelcomeView view = new WelcomeView("Welcome to WareHouse MiNet");
		IUserService model = new UserServiceImpl();
		WelcomeController controller = new WelcomeController(model, view);
		if (context == null) {
			context = MenuContext.WELCOME;
		} else if (context == MenuContext.LOGIN) {
			view.setTitle("Login menu options:");
		}
		controller.setMenuVisible(context == MenuContext.WELCOME);
		controller.requestUserInput(context, currentUser);
	}

}
