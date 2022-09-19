package com.novare.minet.service;

import java.util.List;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Product;
import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;

public interface IMiNetService {

	String USER_STORAGE = "assets/user.json";
	String INVENTROY_STORAGE = "assets/inventories.json";
	String PRODUCT_STORAGE = "assets/products.json";
	String ORDER_STORAGE = "assets/orders.json";
	String REPORT_STORAGE = "assets/reports.json";
	String SUPPLIER_STORAGE = "assets/suppliers.json";
	String TRANSACTION_STORAGE = "assets/transactions.json";
	

	void handleOption(int selectedOption, User currentUser) throws Exception;

	boolean isValidUser(User current) throws Exception;

	Inventory updateInventory(Inventory inventory) throws Exception;

	List<Supplier> getAllSuppliers() throws Exception;

	List<Product> getAllProducts() throws Exception;

	List<Inventory> getAllOutOfStockProducts() throws Exception;

	List<Product> findByProductNameOrId(String search) throws Exception;

	Inventory findInventoryByProductId(Integer prodcutId) throws Exception;
}
