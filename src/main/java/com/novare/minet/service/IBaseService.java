package com.novare.minet.service;

import java.util.List;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Product;
import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;

public interface IBaseService {

	void handleOption(int selectedOption, User currentUser) throws Exception;

	boolean isValidUser(User current) throws Exception;

	Inventory updateInventory(Inventory inventory) throws Exception;

	List<Supplier> getAllSuppliers() throws Exception;

	List<Product> getAllProducts() throws Exception;

	List<Inventory> getAllOutOfStockProducts() throws Exception;

	List<Product> findByProductNameOrId(String search) throws Exception;

	Inventory findInventoryByProductId(Integer prodcutId) throws Exception;
}
