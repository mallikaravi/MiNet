package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.IInventoryService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.InventoryView;

public class InventoryController extends BaseController {

	public InventoryController(IInventoryService model, InventoryView view) {
		super(model, view);
	}

	@Override
	public IInventoryService getModel() {
		return (IInventoryService) super.getModel();
	}

	@Override
	public InventoryView getView() {
		return (InventoryView) super.getView();
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case LIST -> displayInventoryList();
			case CREATE -> createInventory();
			case DELETE -> deleteInventory();
			case EDIT -> editInventory();
			default -> {
				selectedOption = getView().getUserInput();
			}
			}
			getModel().handleOption(selectedOption, getUserSession());
		} catch (Exception e) {
			getView().printInvalidOption();
			getView().printUserRequest();
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		}
	}

	private void editInventory() {
	}

	private void deleteInventory() {
	}

	private void createInventory() {
	}

	private void displayInventoryList() {
	}
}
