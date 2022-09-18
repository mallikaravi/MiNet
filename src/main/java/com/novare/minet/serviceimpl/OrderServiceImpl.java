package com.novare.minet.serviceimpl;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.OrderMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.service.IOrderService;
import com.novare.minet.util.MenuContext;

public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {

		switch (selectedOption) {
		case 0 -> {
			switch (currentUser.getRole()) {
			case ADMIN -> new AdminMenuAction(MenuContext.ADMIN, currentUser);
			case MANAGER -> new AdminMenuAction(MenuContext.MANAGER, currentUser);
			case CASHIER -> new AdminMenuAction(MenuContext.CASHIER, currentUser);
			default -> new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
			}
		}
		case 1 -> {
			new OrderMenuAction(MenuContext.ORDER_LIST, currentUser);
		}
		case 2 -> {
			new OrderMenuAction(MenuContext.CREATE_INVENTORY, currentUser);
		}
		case 3 -> {
			new OrderMenuAction(MenuContext.EDIT_ORDER, currentUser);
		}
		case 4 -> {
			new OrderMenuAction(MenuContext.DELETE_ORDER, currentUser);
		}
		case 5 -> {
			new OrderMenuAction(MenuContext.PENDING_ORDERS, currentUser);
		}
		case 6 -> {
			new OrderMenuAction(MenuContext.SEARCH_ORDER, currentUser);
		}
		case 7 -> {
			new OrderMenuAction(MenuContext.ORDER_HISTORY, currentUser);
		}
		default -> throw new IndexOutOfBoundsException();
		}

	}

}
