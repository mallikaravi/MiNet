package com.novare.minet.service;

import com.novare.minet.model.User;

public interface IUserService extends IMiNetService {
	User findByUserName(String userName) throws Exception;

	User login(User user) throws Exception;

	User createUser(User user) throws Exception;
}
