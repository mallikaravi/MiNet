package com.novare.minet.serviceimpl;

import java.util.Iterator;
import java.util.List;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IUserService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;

public class UserServiceImpl extends MiNetServiceImpl implements IUserService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 0 -> {
			System.exit(0);
		}
		case 1 -> {
			new WelcomeMenuAction(MenuContext.LOGIN, null);
		}
		case 2 -> {
			new WelcomeMenuAction(MenuContext.SIGNUP, null);
		}
		case 3 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, null);
		}
		case 4 -> {
			new AdminMenuAction(MenuContext.ADMIN, currentUser);
		}
		case 5 -> {
			new ManagerMenuAction(MenuContext.MANAGER, currentUser);
		}
		case 6 -> {
			new CashierMenuAction(MenuContext.CASHIER, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public User findByUserName(String userName) throws Exception {
		List<User> users = ServiceUtil.loadModel(User.class, USER_STORAGE);
		for (User user : users) {
			if (user.getUserName().equals(userName)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User deleteUser(User user) throws Exception {
		List<User> users = ServiceUtil.loadModel(User.class, USER_STORAGE);
		users.remove(user);
		ServiceUtil.saveModel(users, USER_STORAGE);
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
		ServiceUtil.saveModel(users, USER_STORAGE);
		return user;
	}

	@Override
	public User updatePassword(User user) throws Exception {
		return updateUser(user);
	}

	@Override
	public User createUser(User user) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<User> users = ServiceUtil.loadModel(User.class, USER_STORAGE);
		user.setPassWord(user.getPassWord());
		user.generateId();
		users.add(user);
		ServiceUtil.saveModel(users, USER_STORAGE);
		return user;
	}

	@Override
	public User login(User user) throws Exception {
		List<User> users = ServiceUtil.loadModel(User.class, USER_STORAGE);
		for (User cache : users) {
			boolean userName = cache.getUserName().equals(user.getUserName());
			boolean password = cache.getPassWord().equals(user.getPassWord());
			if (userName && password) {
				return cache;
			}
		}
		return null;
	}
}