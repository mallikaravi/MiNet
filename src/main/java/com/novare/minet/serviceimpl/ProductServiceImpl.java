package com.novare.minet.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.ProductMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.Inventory;
import com.novare.minet.model.Product;
import com.novare.minet.model.User;
import com.novare.minet.service.IProductService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;

public class ProductServiceImpl extends BaseServiceImpl implements IProductService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 0 -> {
			switch (currentUser.getRole()) {
			case ADMIN -> new AdminMenuAction(MenuContext.ADMIN, currentUser);
			case MANAGER -> new ManagerMenuAction(MenuContext.MANAGER, currentUser);
			case CASHIER -> new CashierMenuAction(MenuContext.CASHIER, currentUser);
			default -> new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
			}
		}
		case 1 -> {
			new ProductMenuAction(MenuContext.LIST, currentUser);
		}
		case 2 -> {
			new ProductMenuAction(MenuContext.CREATE, currentUser);
		}
		case 3 -> {
			new ProductMenuAction(MenuContext.EDIT, currentUser);
		}
		case 4 -> {
			new ProductMenuAction(MenuContext.DELETE, currentUser);
		}
		case 5 -> {
			new ProductMenuAction(MenuContext.SEARCH, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}

	}

	@Override
	public Product create(Product product) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Product> products = ServiceUtil.loadModel(Product.class, STORAGE);
		product.generateId();
		product.addHistories(getCurrentUser());
		products.add(product);
		ServiceUtil.saveModel(products, STORAGE);
		return product;
	}

	@Override
	public Inventory create(Inventory inventory) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Inventory> inventories = ServiceUtil.loadModel(Inventory.class, STORAGE);
		inventory.generateId();
		inventory.addHistories(getCurrentUser());
		inventories.add(inventory);
		ServiceUtil.saveModel(inventories, STORAGE);
		return inventory;
	}

	@Override
	public Product update(Product product) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Product> products = ServiceUtil.loadModel(Product.class, STORAGE);
		Iterator<Product> iterator = products.iterator();
		while (iterator.hasNext()) {
			Product next = iterator.next();
			if (next.getFullName().equals(product.getFullName()) && next.getId() == product.getId()) {
				iterator.remove();
			}
		}
		product.addHistories(getCurrentUser());
		products.add(product);
		ServiceUtil.saveModel(products, STORAGE);
		return product;
	}

	@Override
	public Product delete(Product product) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Product> products = ServiceUtil.loadModel(Product.class, STORAGE);
		products.remove(product);
		ServiceUtil.saveModel(products, STORAGE);
		return product;
	}

	@Override
	public Product findByShortName(String shortName) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Product> products = ServiceUtil.loadModel(Product.class, STORAGE);
		for (Product product : products) {
			if (product.getShortName().equals(shortName)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public List<Product> find(String search) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Product> products = ServiceUtil.loadModel(Product.class, STORAGE);
		List<Product> result = new ArrayList<>();
		for (Product product : products) {
			boolean contains = product.getFullName().toLowerCase().contains(search)
					|| product.getShortName().toLowerCase().contains(search);
			if (contains) {
				result.add(product);
			}
		}
		return result;
	}

	@Override
	public Inventory delete(Inventory inventory) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Inventory> inventories = ServiceUtil.loadModel(Inventory.class, STORAGE);
		inventories.remove(inventory);
		ServiceUtil.saveModel(inventories, STORAGE);
		return inventory;
	}

	@Override
	public List<Inventory> getAll() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Inventory> inventories = ServiceUtil.loadModel(Inventory.class, STORAGE);
		return inventories;
	}
}
