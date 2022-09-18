package com.novare.minet.action;

import com.novare.minet.controller.OrderController;
import com.novare.minet.model.User;
import com.novare.minet.service.IOrderService;
import com.novare.minet.serviceimpl.OrderServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.OrderView;

public class OrderMenuAction extends BaseMenuAction {

	public OrderMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case CREATE_ORDER -> title = "Create Order Option:";
		case DELETE_ORDER -> title = "Delete Order Option:";
		case EDIT_ORDER -> title = "Delete Order Option :";
		case ORDER_LIST -> title = "List of Orders Option :";
		case PENDING_ORDERS -> title = "Pending Order Option:";
		case SEARCH_ORDER -> title = "Search Order Option :";
		case ORDER_HISTORY -> title = "Display Order History Option :";
		default -> title = "Order Option:";
		}
		OrderView view = new OrderView(title);
		IOrderService model = new OrderServiceImpl();
		OrderController controller = new OrderController(model, view);
		controller.setMenuVisible(context == MenuContext.ORDER);
		controller.requestUserInput(context, currentUser);
	}
}
