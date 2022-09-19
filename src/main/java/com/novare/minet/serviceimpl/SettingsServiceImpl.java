package com.novare.minet.serviceimpl;

import java.util.Iterator;
import java.util.List;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.SettingsMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.ISettingsService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;

public class SettingsServiceImpl extends MiNetServiceImpl implements ISettingsService {

	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 0 -> {
			switch (currentUser.getRole()) {
			case ADMIN -> new AdminMenuAction(MenuContext.ADMIN, currentUser);
			case MANAGER -> new ManagerMenuAction(MenuContext.MANAGER, currentUser);
			case CASHIER -> new CashierMenuAction(MenuContext.CASHIER, currentUser);
			default -> new WelcomeMenuAction(MenuContext.WELCOME, null);
			}
		}
		case 1 -> {
			new SettingsMenuAction(MenuContext.UPDATE_USER, currentUser);
		}
		case 2 -> {
			new SettingsMenuAction(MenuContext.CHANGE_PASSWORD, currentUser);
		}
		case 3 -> {
			new SettingsMenuAction(MenuContext.DELETE_USER, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}
	}

	public User deleteUser(User user) throws Exception {
		List<User> users = ServiceUtil.loadModel(User.class, USER_STORAGE);
		users.remove(user);
		ServiceUtil.saveUsers(users);
		return user;
	}

	@Override
	public User updateUser(User user) throws Exception {
		List<User> users = ServiceUtil.loadModel(User.class, USER_STORAGE);
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User next = iterator.next();
			if (next.getFullName().equals(user.getFullName())) {
				iterator.remove();
			}
		}
		user.setPassWord(user.getPassWord());
		users.add(user);
		ServiceUtil.saveUsers(users);
		return user;
	}

	@Override
	public User updatePassword(User user) throws Exception {
		return updateUser(user);
	}

}
