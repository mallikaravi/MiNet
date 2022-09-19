package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.ISettingsService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;
import com.novare.minet.view.SettingsView;

public class SettingsController extends MiNetController {

	public SettingsController(ISettingsService model, SettingsView view) {
		super(model, view);
	}

	@Override
	public ISettingsService getModel() {
		return (ISettingsService) super.getModel();
	}

	@Override
	public SettingsView getView() {
		return (SettingsView) super.getView();
	}

	@Override
	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case UPDATE_USER -> {
				updateProfile();
			}
			case CHANGE_PASSWORD -> {
				changePassword();
			}
			case DELETE_USER -> {
				if (deleteUser()) {
					setUserSession(null);
				}
			}
			default -> {
				int option = getView().getUserInput();
				selectedOption = option;
			}
			}
			getModel().handleOption(selectedOption, getUserSession());
		} catch (Exception e) {
			getView().printInvalidOption();
			getView().printUserRequest();
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		}
	}

	/**
	 * This method is used to delete our profile in the application
	 * 
	 * @return true
	 * @throws Exception
	 */
	private boolean deleteUser() throws Exception {
		boolean askConfirmation = getView().askUserDeletion();
		if (askConfirmation) {
			getModel().deleteUser(getUserSession());
			getView().printSuccessMessage();
			return true;
		}
		return false;
	}

	/**
	 * This method is used to change password
	 * 
	 * @throws Exception
	 */
	private void changePassword() throws Exception {
		String askUserPasswordToChange = getView().askUserPassword();
		if (!askUserPasswordToChange.isEmpty()) {
			getUserSession().setPassWord(ServiceUtil.encrypt(askUserPasswordToChange));
		}
		getModel().updatePassword(getUserSession());
		getView().printSuccessMessage();
		getView().waitForDecision();
	}

	/**
	 * This method is used to update our profile in the application by changing our
	 * username and password
	 * 
	 * @throws Exception
	 */
	private void updateProfile() throws Exception {
		String askUserNameToChange = getView().askUserName();
		if (!askUserNameToChange.isEmpty()) {
			getUserSession().setUserName(askUserNameToChange);
		}
		String askUserPasswordToChange = getView().askUserPassword();
		if (!askUserPasswordToChange.isEmpty()) {
			getUserSession().setPassWord(ServiceUtil.encrypt(askUserPasswordToChange));
		}
		getModel().updateUser(getUserSession());
		getView().printSuccessMessage();
		getView().waitForDecision();

	}
}
