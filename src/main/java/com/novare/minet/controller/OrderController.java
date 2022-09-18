package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.IOrderService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.OrderView;

public class OrderController extends BaseController {

	public OrderController(IOrderService model, OrderView view) {
		super(model, view);
	}

	@Override
	public IOrderService getModel() {
		return (IOrderService) super.getModel();
	}

	@Override
	public OrderView getView() {
		return (OrderView) super.getView();
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case CREATE_ORDER -> createOrder();
			case DELETE_ORDER -> deleteOrder();
			case EDIT_ORDER -> editOrder();
			case ORDER_LIST -> displayOrders();
			case PENDING_ORDERS -> displayPendingOrders();
			case SEARCH_ORDER -> searchOrders();
			case ORDER_HISTORY -> displayOrderHistory();
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

	private void displayOrderHistory() {
	}

	private void editOrder() {
	}

	private void deleteOrder() {
	}

	private void searchOrders() {
	}

	private void displayPendingOrders() {
	}

	private void displayOrders() {
	}

	private void createOrder() {
	}

}
