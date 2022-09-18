package com.novare.minet.service;

import com.novare.minet.model.User;

public interface IUserService extends IBaseService {
	User findByUserName(String userName) throws Exception;

	User deleteUser(User user) throws Exception;

	User updateUser(User user) throws Exception;

	User updatePassword(User user) throws Exception;

	User login(User user) throws Exception;

	User createUser(User user) throws Exception;
}
