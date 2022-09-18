package com.novare.minet.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Product;
import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;
import com.novare.minet.service.IBaseService;
import com.novare.minet.service.IInventoryService;
import com.novare.minet.service.IProductService;
import com.novare.minet.service.ISupplierService;
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
	public Inventory update(Inventory inventory) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Inventory> inventories = ServiceUtil.loadModel(Inventory.class, IInventoryService.STORAGE);
		Iterator<Inventory> iterator = inventories.iterator();
		while (iterator.hasNext()) {
			Inventory next = iterator.next();
			if (next.getId() == inventory.getId()) {
				iterator.remove();
			}
		}
		inventory.addHistories(getCurrentUser());
		inventories.add(inventory);
		ServiceUtil.saveModel(inventories, IInventoryService.STORAGE);
		return inventory;
	}

	@Override
	public List<Supplier> getAllSuppliers() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Supplier> suppliers = ServiceUtil.loadModel(Supplier.class, ISupplierService.STORAGE);
		return suppliers;
	}

	@Override
	public List<Product> getAllProducts() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Product> products = ServiceUtil.loadModel(Product.class, IProductService.STORAGE);
		return products;
	}

	@Override
	public List<Inventory> getAllOutOfStockProducts() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Inventory> inventories = ServiceUtil.loadModel(Inventory.class, IInventoryService.STORAGE);
		List<Inventory> result = new ArrayList<Inventory>();
		for (Inventory inventory : inventories) {
			if (inventory.getAvailQty() <= inventory.getProduct().getMinQty()) {
				result.add(inventory);
			}
		}
		return result;
	}
}
