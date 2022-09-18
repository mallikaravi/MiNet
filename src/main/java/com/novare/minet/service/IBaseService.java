package com.novare.minet.service;

import java.util.List;

import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;

public interface IBaseService {

	void handleOption(int selectedOption, User currentUser) throws Exception;

	boolean isValidUser(User current) throws Exception;

	List<Supplier> getAllSuppliers() throws Exception;

}
