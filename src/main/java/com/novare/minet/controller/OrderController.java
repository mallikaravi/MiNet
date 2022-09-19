package com.novare.minet.controller;

import java.util.List;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Order;
import com.novare.minet.model.OrderStatus;
import com.novare.minet.model.Product;
import com.novare.minet.model.Role;
import com.novare.minet.model.User;
import com.novare.minet.service.IOrderService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.OrderView;

public class OrderController extends MiNetController {

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
			case LIST -> displayOrders();
			case CREATE -> createOrder();
			case DELETE -> deleteOrder();
			case WAITING_ORDERS -> displayWaitingOrders(); 
			case PENDING_ORDERS -> displayPendingOrders();
			case SEARCH -> searchOrders();
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

	private void deleteOrder() throws Exception {
		List<Order> allOrders = getModel().getAll();
		if (allOrders == null || allOrders.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}
		String generateOrderTable = generateOrderTable(allOrders);
		int selection = getView().askForDelete(generateOrderTable, allOrders);
		if (selection > -1) {
			Order order = allOrders.get(selection);
			Inventory inventory = getModel().findInventoryByProductId(order.getProduct().getId());
			if (order.getStatus() == OrderStatus.WAITING) {
				// Update Inventory
				inventory.setOrderedQty(inventory.getOrderedQty() - order.getQuantity());
				getModel().updateInventory(inventory);
			}
			getModel().delete(order);

			getView().printSaveMessage();
			getView().waitForDecision();
		}
	}

	private void searchOrders() throws Exception {
		String askSearch = getView().askSearch();
		List<Order> search = getModel().search(askSearch);
		if (search.isEmpty()) {
			getView().displayResultNotFound();
			return;
		} else {
			String generateOrderTable = generateOrderTable(search);
			getView().printMessage(generateOrderTable);

			boolean details = getView().askDisplay();
			if (details) {
				int selection = getView().askForChoose("", search);
				Order order = search.get(selection);
				getView().printMessage(generateOrderHistoryTable(order));
			}
		}
		getView().waitForDecision();
	}

	private void displayWaitingOrders() throws Exception {
		List<Order> findAllWaiting = getModel().findAllWaiting();
		if (findAllWaiting == null || findAllWaiting.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}
		String generateOrderTable = generateOrderTable(findAllWaiting);

		int selection = getView().askOrderSelectionToReceive(generateOrderTable, findAllWaiting);
		if (selection > -1) {
			Order order = findAllWaiting.get(selection);
			Inventory inventory = getModel().findInventoryByProductId(order.getProduct().getId());
			getView().printMessage(generateOrderTableWithInventory(order, inventory));
			if (getUserSession().getRole() != Role.CASHIER) {

				boolean approve = getView().askForAprrove();
				if (approve) {
					order.setStatus(OrderStatus.DELIVERED);
					getModel().update(order);

					// Update Inventory
					inventory.setOrderedQty(inventory.getOrderedQty() - order.getQuantity());
					inventory.setAvailQty(inventory.getAvailQty() + order.getQuantity());
					getModel().updateInventory(inventory);

					getView().printSaveMessage();
				}
			}
			getView().waitForDecision();
		}
	}

	private void displayPendingOrders() throws Exception {
		List<Order> findAllPending = getModel().findAllPending();
		if (findAllPending == null || findAllPending.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}
		String generateOrderTable = generateOrderTable(findAllPending);
		int selection = getView().askOrderSelectionToApprove(generateOrderTable, findAllPending);
		if (selection > -1) {
			Order order = findAllPending.get(selection);
			Inventory inventory = getModel().findInventoryByProductId(order.getProduct().getId());
			getView().printMessage(generateOrderTableWithInventory(order, inventory));
			if (getUserSession().getRole() != Role.CASHIER) {
				boolean approve = getView().askForAprrove();
				if (approve) {
					order.setStatus(OrderStatus.WAITING);
					getModel().update(order);

					// Update Inventory
					inventory.setOrderedQty(order.getQuantity());
					getModel().updateInventory(inventory);

					getView().printSaveMessage();
				}
			}
			getView().waitForDecision();
		}
	}

	private void displayOrders() throws Exception {
		List<Order> orders = getModel().getAll();
		if (orders == null || orders.isEmpty()) {
			getView().displayResultNotFound();
		} else {
			getView().printMessage(generateOrderTable(orders));
			boolean details = getView().askDisplay();
			if (details) {
				int selection = getView().askForChoose("", orders);
				Order order = orders.get(selection);
				getView().printMessage(generateOrderHistoryTable(order));
			}
		}
		getView().waitForDecision();

	}

	private void createOrder() throws Exception {
		List<Inventory> productList = getModel().getAllOutOfStockProducts();
		if (productList == null || productList.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}
		String inventoryTable = generateProductTableFromInventories(productList);
		int selection = getView().askForCreate(inventoryTable, productList);
		if (selection > -1) {
			Inventory inventory = productList.get(selection);
			Product selectedProduct = inventory.getProduct();
			Double orderQty = getView().askForOrderQuantity();

			Order order = new Order(selectedProduct, orderQty, selectedProduct.getCostPrice() * orderQty,
					selectedProduct.getDefaultSupplier(), OrderStatus.PENDING, getUserSession());
			getModel().create(order);
			getView().printSaveMessage();
			getView().waitForDecision();
		}
	}

}
