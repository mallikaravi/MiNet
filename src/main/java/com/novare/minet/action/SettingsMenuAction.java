package com.novare.minet.action;

import com.novare.minet.controller.SettingsController;
import com.novare.minet.model.User;
import com.novare.minet.service.ISettingsService;
import com.novare.minet.serviceimpl.SettingsServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.SettingsView;

public class SettingsMenuAction extends MiNetMenuAction {

	public SettingsMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case UPDATE_USER -> title = "Edit Profile Option";
		case CHANGE_PASSWORD -> title = "Change Password Option";
		case DELETE_USER -> title = "Delete User Option";
		default -> title = "Settings Menu options";
		}
		SettingsView view = new SettingsView(title);
		ISettingsService model = new SettingsServiceImpl();
		SettingsController controller = new SettingsController(model, view);
		controller.setMenuVisible(context == MenuContext.SETTINGS);
		controller.requestUserInput(context, currentUser);

	}

}
