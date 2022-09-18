package com.novare.minet.controller;

import java.util.Objects;

import javax.security.sasl.AuthenticationException;
import com.novare.minet.model.User;
import com.novare.minet.service.IBaseService;
import com.novare.minet.serviceimpl.BaseServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.BaseView;

public class BaseController {

	private IBaseService model;
	private BaseView view;
	private boolean menuVisible;
	private User userSession;

	public BaseController(IBaseService model, BaseView view) {
		setModel(model);
		setView(view);
		setMenuVisible(true);
	}

	/**
	 * @return the currentUser
	 */
	public User getUserSession() {
		return userSession;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setUserSession(User currentUser) {
		this.userSession = currentUser;
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		setUserSession(currentUser);
		((BaseServiceImpl)model).setCurrentUser(currentUser);
		if (currentUser != null && !getModel().isValidUser(currentUser)) {
			throw new AuthenticationException();
		}
		if (isMenuVisible()) {
			getView().setMenuOptions(getView().getMenuOptions());
			getView().printUserRequest();
		}
	}

	/**
	 * @return the model
	 */
	public IBaseService getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(IBaseService model) {
		this.model = model;
	}

	/**
	 * @return the menuVisible
	 */
	public boolean isMenuVisible() {
		return menuVisible;
	}

	/**
	 * @param menuVisible the menuVisible to set
	 */
	public void setMenuVisible(boolean menuVisible) {
		this.menuVisible = menuVisible;
	}

	/**
	 * @return the view
	 */
	public BaseView getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(BaseView view) {
		this.view = view;
	}

	protected boolean isNull(Object object) {
		return Objects.isNull(object);
	}

	protected boolean isNotNull(Object object) {
		return Objects.nonNull(object);
	}
}
