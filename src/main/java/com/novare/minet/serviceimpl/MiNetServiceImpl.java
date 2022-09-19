package com.novare.minet.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Product;
import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;
import com.novare.minet.service.IMiNetService;
import com.novare.minet.service.IProductService;
import com.novare.minet.service.ISupplierService;
import com.novare.minet.util.ServiceUtil;

public abstract class MiNetServiceImpl implements IMiNetService {

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
		List<User> users = ServiceUtil.loadModel(User.class, USER_STORAGE);
		return users.contains(current);
	}

	@Override
	public Inventory updateInventory(Inventory inventory) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Inventory> inventories = ServiceUtil.loadModel(Inventory.class, INVENTROY_STORAGE);
		Iterator<Inventory> iterator = inventories.iterator();
		while (iterator.hasNext()) {
			Inventory next = iterator.next();
			if (next.getId() == inventory.getId()) {
				iterator.remove();
			}
		}
		inventory.addHistories(getCurrentUser());
		inventories.add(inventory);
		ServiceUtil.saveModel(inventories, INVENTROY_STORAGE);
		return inventory;
	}

	@Override
	public List<Supplier> getAllSuppliers() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Supplier> suppliers = ServiceUtil.loadModel(Supplier.class, ISupplierService.SUPPLIER_STORAGE);
		return suppliers;
	}

	@Override
	public List<Product> getAllProducts() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Product> products = ServiceUtil.loadModel(Product.class, IProductService.PRODUCT_STORAGE);
		return products;
	}

	@Override
	public List<Inventory> getAllOutOfStockProducts() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Inventory> inventories = ServiceUtil.loadModel(Inventory.class, INVENTROY_STORAGE);
		List<Inventory> result = new ArrayList<Inventory>();
		for (Inventory inventory : inventories) {
			if (inventory.getAvailQty() <= inventory.getProduct().getMinQty()) {
				result.add(inventory);
			}
		}
		return result;
	}

	@Override
	public Inventory findInventoryByProductId(Integer prodcutId) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Inventory> inventories = ServiceUtil.loadModel(Inventory.class, INVENTROY_STORAGE);
		for (Inventory inventory : inventories) {
			if (inventory.getProduct().getId() == prodcutId) {
				return inventory;
			}
		}
		return null;
	}

	@Override
	public List<Product> findByProductNameOrId(String search) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Product> products = ServiceUtil.loadModel(Product.class, IProductService.PRODUCT_STORAGE);
		List<Product> result = new ArrayList<>();
		for (Product product : products) {
			boolean contains = false;
			try {
				Integer productId = Integer.parseInt(search);
				contains = product.getId() == productId;
			} catch (Exception e) {
				contains = product.getFullName().toLowerCase().contains(search.toLowerCase())
						|| product.getShortName().toLowerCase().contains(search.toLowerCase());
			}
			if (contains) {
				result.add(product);
			}
		}
		return result;
	}

}
