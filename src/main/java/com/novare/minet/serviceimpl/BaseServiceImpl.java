package com.novare.minet.serviceimpl;

import java.util.List;

import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;
import com.novare.minet.service.IBaseService;
import com.novare.minet.util.ServiceUtil;

public abstract class BaseServiceImpl implements IBaseService {

	private User currentUser;

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public boolean isValidUser(User current) throws Exception {
		List<User> users = ServiceUtil.loadUsers();
		return users.contains(current);
	}

	@Override
	public List<Supplier> getAllSuppliers() throws Exception {
		return null;
	}

}
