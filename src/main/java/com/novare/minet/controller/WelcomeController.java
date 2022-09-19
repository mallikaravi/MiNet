package com.novare.minet.controller;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import com.novare.minet.model.Role;
import com.novare.minet.model.User;
import com.novare.minet.service.IUserService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;
import com.novare.minet.view.WelcomeView;

public class WelcomeController extends MiNetController {
	private User newUser = new User();

	public WelcomeController(IUserService model, WelcomeView view) {
		super(model, view);
	}

	@Override
	public IUserService getModel() {
		return (IUserService) super.getModel();
	}

	@Override
	public WelcomeView getView() {
		return (WelcomeView) super.getView();
	}

	@Override
	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case SIGNUP -> {
				createUser();
				selectedOption = 1;
			}
			case LOGIN -> {
				try {
					login();
					switch (getUserSession().getRole()) {
					case ADMIN -> selectedOption = 4;
					case MANAGER -> selectedOption = 5;
					case CASHIER -> selectedOption = 6;
					default -> selectedOption = 3;
					}
				} catch (AuthenticationException e) {
					selectedOption = 3;
				}
			}
			case LOGOUT -> {
				selectedOption = 3;
			}
			default -> {
				selectedOption = getView().getUserInput();
			}
			}
			getModel().handleOption(selectedOption, getUserSession());
		} catch (FileNotFoundException e) {
			getView().printMessage(e.getMessage());
			getView().goodBye();
		} catch (Exception e) {
			getView().printInvalidOption();
			getView().printUserRequest();
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		}
	}

	private void login() throws Exception {
		if (newUser.getUserName() == null) {
			newUser.setUserName(getView().askUserName());
		}
		if (newUser.getPassWord() == null) {
			newUser.setPassWord(ServiceUtil.encrypt(getView().askUserPassword()));
		}
		User login = getModel().login(newUser);
		if (login == null) {
			if (getView().askForSignUp()) {
				throw new AuthenticationException();
			}
		}
		setUserSession(login);

	}

	/**
	 * this method is used to create a user by sign up
	 * 
	 * @throws Exception
	 */
	private void createUser() throws Exception {
		if (newUser.getFullName() == null) {
			newUser.setFullName(getView().askUserFullName());
		}

		if (newUser.getUserName() == null) {
			newUser.setUserName(getView().askUserName());
			if (getModel().isValidUser(newUser)) {
				newUser.setUserName("");
				throw new Exception("User Already exist !");
			}
		}
		if (newUser.getPassWord() == null) {
			newUser.setPassWord(ServiceUtil.encrypt(getView().askUserPassword()));
		}

		if (newUser.getPhoneNumber() == null) {
			newUser.setPhoneNumber(getView().askPhoneNumber());
		}

		if (newUser.getAddress() == null) {
			newUser.setAddress(getView().askAddress());
		}

		if (newUser.getEmail() == null) {
			newUser.setEmail(getView().askEmail());
		}

		if (newUser.getRole() == null) {
			List<Role> roles = Arrays.asList(Role.values());
			int selectedOptionFromMenu = getView().askRole(roles);
			newUser.setRole(roles.get(selectedOptionFromMenu));
		}
		getModel().createUser(newUser);

	}
}
