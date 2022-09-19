package com.novare.minet.action;

import com.novare.minet.controller.OrderController;
import com.novare.minet.model.User;
import com.novare.minet.service.IOrderService;
import com.novare.minet.serviceimpl.OrderServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.OrderView;

public class OrderMenuAction extends MiNetMenuAction {

	public OrderMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case CREATE -> title = "Create Order Option:";
		case DELETE -> title = "Delete Order Option:";
		case EDIT -> title = "Delete Order Option :";
		case LIST -> title = "List of Orders Option :";
		case PENDING_ORDERS -> title = "Pending Order Option:";
		case SEARCH -> title = "Search Order Option :";
		case ORDER_HISTORY -> title = "Display Order History Option :";
		default -> title = "Order Option:";
		}
		OrderView view = new OrderView(getAppHeader(),title);
		IOrderService model = new OrderServiceImpl();
		OrderController controller = new OrderController(model, view);
		controller.setMenuVisible(context == MenuContext.ORDER);
		controller.requestUserInput(context, currentUser);
	}
}
