package com.novare.minet.serviceimpl;

import java.util.Iterator;
import java.util.List;

import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.ProductMenuAction;
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
			new CashierMenuAction(MenuContext.CASHIER, currentUser);
		}
		case 1 -> {
			new ProductMenuAction(MenuContext.PRODUCT_LIST, currentUser);
		}
		case 2 -> {
			new ProductMenuAction(MenuContext.CREATE_PRODUCT, currentUser);
		}
		case 3 -> {
			new ProductMenuAction(MenuContext.EDIT_PRODUCT, currentUser);
		}
		case 4 -> {
			new ProductMenuAction(MenuContext.DELETE_PRODUCT, currentUser);
		}
		case 5 -> {
			new ProductMenuAction(MenuContext.SEARCH_PRODUCT, currentUser);
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
	public List<Product> getAll() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Product> products = ServiceUtil.loadModel(Product.class, STORAGE);
		return products;
	}

}
