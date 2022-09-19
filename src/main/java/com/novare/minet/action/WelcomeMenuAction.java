package com.novare.minet.action;

import com.novare.minet.controller.WelcomeController;
import com.novare.minet.model.User;
import com.novare.minet.service.IUserService;
import com.novare.minet.serviceimpl.UserServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.WelcomeView;

public class WelcomeMenuAction extends MiNetMenuAction {

	public WelcomeMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		WelcomeView view = new WelcomeView(getAppHeader(),"Login menu options:");
		IUserService model = new UserServiceImpl();
		WelcomeController controller = new WelcomeController(model, view);
		controller.setMenuVisible(context == MenuContext.WELCOME);
		controller.requestUserInput(context, currentUser);
	}

}
