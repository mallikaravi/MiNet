package com.novare.minet.serviceimpl;

import java.util.Iterator;
import java.util.List;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.InventoryMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.Inventory;
import com.novare.minet.model.User;
import com.novare.minet.service.IInventoryService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;

public class InventoryServiceImpl extends BaseServiceImpl implements IInventoryService {

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
			new InventoryMenuAction(MenuContext.LIST, currentUser);
		}
		case 2 -> {
			new InventoryMenuAction(MenuContext.CREATE, currentUser);
		}
		case 3 -> {
			new InventoryMenuAction(MenuContext.EDIT, currentUser);
		}
		case 4 -> {
			new InventoryMenuAction(MenuContext.DELETE, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}

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
	public Inventory update(Inventory inventory) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Inventory> inventories = ServiceUtil.loadModel(Inventory.class, STORAGE);
		Iterator<Inventory> iterator = inventories.iterator();
		while (iterator.hasNext()) {
			Inventory next = iterator.next();
			if (next.getId() == inventory.getId()) {
				iterator.remove();
			}
		}
		inventory.addHistories(getCurrentUser());
		inventories.add(inventory);
		ServiceUtil.saveModel(inventories, STORAGE);
		return inventory;
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
