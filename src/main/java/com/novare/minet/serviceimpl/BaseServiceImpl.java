package com.novare.minet.serviceimpl;

import com.novare.minet.model.User;
import com.novare.minet.service.IBaseService;

public abstract class BaseServiceImpl implements IBaseService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValidUser(User current) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User login(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
