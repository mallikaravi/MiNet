package com.novare.minet.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.OrderMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.Order;
import com.novare.minet.model.OrderStatus;
import com.novare.minet.model.User;
import com.novare.minet.service.IOrderService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;

public class OrderServiceImpl extends MiNetServiceImpl implements IOrderService {

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
			new OrderMenuAction(MenuContext.LIST, currentUser);
		}
		case 2 -> {
			new OrderMenuAction(MenuContext.CREATE, currentUser);
		}
		case 3 -> {
			new OrderMenuAction(MenuContext.EDIT, currentUser);
		}
		case 4 -> {
			new OrderMenuAction(MenuContext.DELETE, currentUser);
		}
		case 5 -> {
			new OrderMenuAction(MenuContext.PENDING_ORDERS, currentUser);
		}
		case 6 -> {
			new OrderMenuAction(MenuContext.SEARCH, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}

	}

	@Override
	public Order create(Order order) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Order> orders = ServiceUtil.loadModel(Order.class, ORDER_STORAGE);
		order.generateId();
		order.addHistories(getCurrentUser());
		orders.add(order);
		ServiceUtil.saveModel(orders, ORDER_STORAGE);
		return order;

	}

	@Override
	public Order update(Order order) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Order> orders = ServiceUtil.loadModel(Order.class, ORDER_STORAGE);
		Iterator<Order> iterator = orders.iterator();
		while (iterator.hasNext()) {
			Order next = iterator.next();
			if (next.getId() == order.getId()) {
				iterator.remove();
			}
		}
		order.addHistories(getCurrentUser());
		orders.add(order);
		ServiceUtil.saveModel(orders, ORDER_STORAGE);
		return order;
	}

	@Override
	public Order delete(Order order) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Order> orders = ServiceUtil.loadModel(Order.class, ORDER_STORAGE);
		orders.remove(order);
		ServiceUtil.saveModel(orders, ORDER_STORAGE);
		return order;
	}

	@Override
	public List<Order> findAllPending() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Order> orders = ServiceUtil.loadModel(Order.class, ORDER_STORAGE);
		List<Order> result = new ArrayList<>();
		for (Order order : orders) {
			if (order.getStatus().equals(OrderStatus.PENDING)) {
				filterByUserRole(result, order);
			}
		}
		return result;
	}

	private void filterByUserRole(List<Order> result, Order order) {
		switch (getCurrentUser().getRole()) {
		case CASHIER -> {
			boolean contains = order.getCreatedBy().equals(getCurrentUser());
			if (contains) {
				result.add(order);
			}
		}
		default -> {
			result.add(order);
		}
		}
	}

	@Override
	public List<Order> search(String search) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Order> orders = ServiceUtil.loadModel(Order.class, ORDER_STORAGE);
		List<Order> result = new ArrayList<>();
		for (Order order : orders) {

			boolean contains = false;
			try {
				Integer productId = Integer.parseInt(search);
				contains = order.getId() == productId;
			} catch (Exception e) {
				contains = order.getCreatedBy().getFullName().toLowerCase().contains(search.toLowerCase())
						|| order.getCreatedBy().getUserName().toLowerCase().contains(search.toLowerCase())
						|| order.getSupplier().getName().toLowerCase().contains(search.toLowerCase())
						|| order.getStatus().name().contains(search.toUpperCase());
			}
			if (contains) {
				result.add(order);
			}
		}
		return result;
	}

	@Override
	public List<Order> getAll() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Order> orders = ServiceUtil.loadModel(Order.class, ORDER_STORAGE);
		List<Order> result = new ArrayList<>();
		switch (getCurrentUser().getRole()) {
		case CASHIER -> {
			for (Order order : orders) {
				boolean contains = order.getCreatedBy().equals(getCurrentUser());
				if (contains) {
					result.add(order);
				}
			}
			return result;
		}
		default -> {
			return orders;
		}
		}
	}

	@Override
	public List<Order> findAllWaiting() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Order> orders = ServiceUtil.loadModel(Order.class, ORDER_STORAGE);
		List<Order> result = new ArrayList<>();
		for (Order order : orders) {
			if (order.getStatus().equals(OrderStatus.WAITING)) {
				filterByUserRole(result, order);
			}
		}
		return result;
	}

}
