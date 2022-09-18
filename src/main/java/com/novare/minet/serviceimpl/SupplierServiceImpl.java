package com.novare.minet.serviceimpl;

import java.util.List;

import com.novare.minet.action.SuppliersMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;
import com.novare.minet.service.ISupplierService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;

public class SupplierServiceImpl extends BaseServiceImpl implements ISupplierService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
		case 1 -> {
			new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
		}
		case 2 -> {
			new SuppliersMenuAction(MenuContext.CREATE_SUPPLIER, currentUser);
		}
		case 3 -> {
			new SuppliersMenuAction(MenuContext.EDIT_SUPPLIER, currentUser);
		}
		case 4 -> {
			new SuppliersMenuAction(MenuContext.DELETE_SUPPLIER, currentUser);
		}
		case 5 -> {
			new SuppliersMenuAction(MenuContext.SUPPLIER_LIST, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();

		}
	}

	@Override
	public Supplier create(Supplier supplier) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier update(Supplier supplier) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier delete(Supplier supplier) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Supplier>suppliers = ServiceUtil.loadModel(Supplier.class, STORAGE);
		suppliers.remove(supplier);
		ServiceUtil.saveModel(suppliers, STORAGE);
		return supplier;
	}

	@Override
	public List<Supplier> getAll() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Supplier> suppliers = ServiceUtil.loadModel(Supplier.class, STORAGE);
		return suppliers;
	}

}
