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
		case CREATE -> title = "Create Order Options:";
		case DELETE -> title = "Delete Order Options:";
		case WAITING_ORDERS -> title = "Waiting Order Options:";
		case LIST -> title = "List of Orders Options:";
		case PENDING_ORDERS -> title = "Pending Order Options:";
		case SEARCH -> title = "Search Order Options:";
		default -> title = "Order Options:";
		}
		OrderView view = new OrderView(getAppHeader(),title);
		IOrderService model = new OrderServiceImpl();
		OrderController controller = new OrderController(model, view);
		controller.setMenuVisible(context == MenuContext.ORDER);
		controller.requestUserInput(context, currentUser);
	}
}
