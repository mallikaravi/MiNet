package com.novare.minet.service;

import com.novare.minet.model.User;

public interface ISettingsService  extends IMiNetService{
	User deleteUser(User user) throws Exception;

	User updateUser(User user) throws Exception;

	User updatePassword(User user) throws Exception;

}
