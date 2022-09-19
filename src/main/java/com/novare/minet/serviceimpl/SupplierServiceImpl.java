
package com.novare.minet.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.SuppliersMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;
import com.novare.minet.service.ISupplierService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;

public class SupplierServiceImpl extends MiNetServiceImpl implements ISupplierService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {

		switch (selectedOption) {
		case 0 -> {
			switch (currentUser.getRole()) {
			case ADMIN -> new AdminMenuAction(MenuContext.ADMIN, currentUser);
			case MANAGER -> new ManagerMenuAction(MenuContext.MANAGER, currentUser);
			case CASHIER -> new CashierMenuAction(MenuContext.CASHIER, currentUser);
			default -> new WelcomeMenuAction(MenuContext.WELCOME, null);
			}
		}
		case 1 -> {
			new SuppliersMenuAction(MenuContext.LIST, currentUser);
		}
		case 2 -> {
			new SuppliersMenuAction(MenuContext.CREATE, currentUser);
		}
		case 3 -> {
			new SuppliersMenuAction(MenuContext.EDIT, currentUser);
		}
		case 4 -> {
			new SuppliersMenuAction(MenuContext.DELETE, currentUser);
		}
		case 5 -> {
			new SuppliersMenuAction(MenuContext.SEARCH, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public Supplier create(Supplier supplier) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Supplier> suppliers = ServiceUtil.loadModel(Supplier.class, SUPPLIER_STORAGE);
		supplier.generateId();
		suppliers.add(supplier);
		ServiceUtil.saveModel(suppliers, SUPPLIER_STORAGE);
		return supplier;
	}

	@Override
	public Supplier update(Supplier supplier) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Supplier> suppliers = ServiceUtil.loadModel(Supplier.class, SUPPLIER_STORAGE);
		Iterator<Supplier> iterator = suppliers.iterator();
		while (iterator.hasNext()) {
			Supplier next = iterator.next();
			if (next.getName().equals(supplier.getName()) && next.getId() == supplier.getId()) {
				iterator.remove();
			}
		}
		suppliers.add(supplier);
		ServiceUtil.saveModel(suppliers, SUPPLIER_STORAGE);
		return supplier;
	}

	@Override
	public Supplier delete(Supplier supplier) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Supplier> suppliers = ServiceUtil.loadModel(Supplier.class, SUPPLIER_STORAGE);
		suppliers.remove(supplier);
		ServiceUtil.saveModel(suppliers, SUPPLIER_STORAGE);
		return supplier;
	}

	@Override
	public List<Supplier> find(String search) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Supplier> suppliers = ServiceUtil.loadModel(Supplier.class, SUPPLIER_STORAGE);
		List<Supplier> result = new ArrayList<>();
		for (Supplier supplier : suppliers) {
			boolean contains = supplier.getName().toLowerCase().contains(search.toLowerCase());
			if (contains) {
				result.add(supplier);
			}
		}
		return result;
	}

}
