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
			case INVENTORY_LIST -> viewInventoryList();
			case CREATE_ORDER -> createOrder();
			case ORDER_LIST -> OrderList();
			case PENDING_ORDERS -> PendingOrder();
			case SEARCH_ORDER -> searchOrder();
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

	private Object searchOrder() {
		return null;
	}

	private Object PendingOrder() {
		return null;
	}

	private Object OrderList() {
		return null;
	}

	private Object createOrder() {
		return null;
	}

	private Object viewInventoryList() {
		return null;
	}
}
