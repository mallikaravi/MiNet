package com.novare.minet.controller;

import java.util.List;
import java.util.Objects;

import javax.security.sasl.AuthenticationException;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Order;
import com.novare.minet.model.OrderHistory;
import com.novare.minet.model.Product;
import com.novare.minet.model.Role;
import com.novare.minet.model.Supplier;
import com.novare.minet.model.Transaction;
import com.novare.minet.model.TransactionItems;
import com.novare.minet.model.User;
import com.novare.minet.service.IMiNetService;
import com.novare.minet.serviceimpl.MiNetServiceImpl;
import com.novare.minet.util.DateUtil;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;
import com.novare.minet.view.BaseView;

public class MiNetController {

	private IMiNetService model;
	private BaseView view;
	private boolean menuVisible;
	private User userSession;

	public MiNetController(IMiNetService model, BaseView view) {
		setModel(model);
		setView(view);
		setMenuVisible(true);
	}

	/**
	 * @return the currentUser
	 */
	public User getUserSession() {
		return userSession;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setUserSession(User currentUser) {
		this.userSession = currentUser;
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		setUserSession(currentUser);
		((MiNetServiceImpl) model).setCurrentUser(currentUser);
		if (currentUser != null && !getModel().isValidUser(currentUser)) {
			throw new AuthenticationException();
		}
		if (isMenuVisible()) {
			getView().setMenuOptions(getView().getMenuOptions());
			getView().printUserRequest();
		}
	}

	/**
	 * @return the model
	 */
	public IMiNetService getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(IMiNetService model) {
		this.model = model;
	}

	/**
	 * @return the menuVisible
	 */
	public boolean isMenuVisible() {
		return menuVisible;
	}

	/**
	 * @param menuVisible the menuVisible to set
	 */
	public void setMenuVisible(boolean menuVisible) {
		this.menuVisible = menuVisible;
	}

	/**
	 * @return the view
	 */
	public BaseView getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(BaseView view) {
		this.view = view;
	}

	protected boolean isNull(Object object) {
		return Objects.isNull(object);
	}

	protected boolean isNotNull(Object object) {
		return Objects.nonNull(object);
	}

	/************************************************************************
	 * Formating the text
	 ***********************************************************************/

	protected String generateProductTable(List<Product> products) throws Exception {

		String LINE_FORMAT = "| %85s |%n";
		String TABLE_FORMAT = "| %-5s| %-5s| %-20.20s| %-15.15s| %10s|  %10s|  %7s|%n";
		String[] COLUMNS = { "S.NO", "ID", "NAME", "SHORT NAME", "PRICE", "AVAIL QTY", "MIN QTY" };

		StringBuilder builder = new StringBuilder();
		builder.append(String.format(LINE_FORMAT, "").replace(' ', '-'));
		builder.append(String.format(TABLE_FORMAT, COLUMNS));
		builder.append(String.format(LINE_FORMAT, "").replace(' ', '-'));

		int count = 1;
		for (Product product : products) {
			Inventory inventory = getModel().findInventoryByProductId(product.getId());
			builder.append(String.format(TABLE_FORMAT, count, product.getId(), product.getFullName(),
					product.getShortName(), product.getSellingPrice(), inventory.getAvailQty(), product.getMinQty()));
			count++;
		}
		builder.append(String.format(LINE_FORMAT, "").replace(' ', '-'));
		return builder.toString();
	}

	protected String generateProductTableFromInventories(List<Inventory> inventories) throws Exception {
		String LINE = "| %85s |%n";
		String TABLE = "| %-5s| %-5s| %-20.20s| %-15.15s| %10s|  %10s|  %7s|%n";
		String[] COLUMNS = { "S.NO", "ID", "NAME", "SHORT NAME", "PRICE", "AVAIL QTY", "MIN QTY" };

		StringBuilder builder = new StringBuilder();
		builder.append(String.format(LINE, "").replace(' ', '-'));
		builder.append(String.format(TABLE, COLUMNS));
		builder.append(String.format(LINE, "").replace(' ', '-'));

		int count = 1;
		for (Inventory inventory : inventories) {
			Product product = inventory.getProduct();
			builder.append(String.format(TABLE, count, product.getId(), product.getFullName(), product.getShortName(),
					product.getSellingPrice(), inventory.getAvailQty(), product.getMinQty()));
			count++;
		}
		builder.append(String.format(LINE, "").replace(' ', '-'));
		return builder.toString();
	}

	protected String generateSupplierTable(List<Supplier> suppliers) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));
		builder.append(String.format("| %-5s| %-5s| %-25.25s| %-25.25s| %18s|%n", "S.NO", "ID", "NAME", "EMAIL",
				"PHONE NUMBER"));
		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));

		int count = 1;
		for (Supplier supplier : suppliers) {
			builder.append(String.format("| %-5s| %-5s| %-25.25s| %-25.25s| %18.18s|%n", count, supplier.getId(),
					supplier.getName(), supplier.getEmail(), supplier.getPhoneNumber()));
			count++;
		}
		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));
		return builder.toString();
	}

	protected String generateOrderTable(List<Order> orders) throws Exception {
		String LINE_WIDTH = "| %110s |%n";
		String TABLE_WIDTH = "| %-4s| %-5s| %-20.20s| %-20.20s| %-8s| %-10s| %-20.20s| %10s|%n";

		StringBuilder builder = new StringBuilder();
		builder.append(String.format(LINE_WIDTH, "").replace(' ', '-'));
		builder.append(String.format(TABLE_WIDTH, "S.NO", "ID", "CREATED BY", "PRODUCT NAME", "QUANTITY", "STATUS",
				"SUPPLIER", "AMOUNT"));
		builder.append(String.format(LINE_WIDTH, "").replace(' ', '-'));

		int count = 1;
		for (Order order : orders) {
			builder.append(String.format(TABLE_WIDTH, count, order.getId(), order.getCreatedBy().getFullName(),
					order.getProduct().getFullName(), order.getQuantity(), order.getStatus().name(),
					order.getSupplier().getName(), order.getTotalAmount()));
			count++;
		}
		builder.append(String.format(LINE_WIDTH, "").replace(' ', '-'));
		return builder.toString();
	}

	protected String generateOrderHistoryTable(Order order) throws Exception {
		String LINE_WIDTH = "| %80s |%n";
		String TABLE_WIDTH = "| %-4s| %-5s| %-20.20s| %-20.20s| %10s|%n";
		String[] COLUMNS = { "S.NO", "ID", "UPDATE BY", "UPDATED ON", "STATUS" };

		StringBuilder builder = new StringBuilder();
		builder.append(String.format(LINE_WIDTH, "").replace(' ', '-'));
		builder.append(String.format(TABLE_WIDTH, COLUMNS));
		builder.append(String.format(LINE_WIDTH, "").replace(' ', '-'));

		int count = 1;
		for (OrderHistory history : order.getHistories()) {
			builder.append(String.format(TABLE_WIDTH, count, history.getId(), history.getUpdateBy().getFullName(),
					DateUtil.toString(history.getUpdatedOn()), history.getOrder().getStatus().name()));
			count++;
		}
		builder.append(String.format(LINE_WIDTH, "").replace(' ', '-'));
		return builder.toString();
	}

	protected String generatePrintReceipt(Transaction transaction) throws Exception {
		String TABLE = " %-30s | %5s | %10s | %15s %n";
		String LINE = " %70s %n";

		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append(ServiceUtil.printHeader(getUserSession()));

		builder.append("\n");
		builder.append(String.format(TABLE, "ITEM NAME", "QTY", "PRICE", "AMOUNT"));
		builder.append(String.format(LINE, "").replace(' ', '-'));
		for (TransactionItems items : transaction.getTransactionItems()) {
			Product product = items.getProduct();
			builder.append(String.format(" %-30.30s  %6s  %11s  %16s %n", product.getShortName(), items.getQuantity(),
					product.getSellingPrice(), items.getTotalAmount()));
		}
		builder.append("\n");
		builder.append(String.format(LINE, "").replace(' ', '-'));
		builder.append(String.format(" TOTAL AMOUNT: %55s %n", transaction.getTotalAmount()));
		builder.append(String.format(LINE, "").replace(' ', '-'));
		builder.append(String.format(" BALANCE: %60s %n", "0.0"));
		builder.append(String.format(LINE, "").replace(' ', '*'));
		builder.append(String.format(" %20s %-20s %10s %n", "", "THANK YOU COME AGAIN", ""));
		builder.append(String.format(LINE, "").replace(' ', '*'));

		return builder.toString();
	}

	protected String generateTransactionTable(List<Transaction> transactions) throws Exception {
		String LINE = "| %100s |%n";
		String GREAT = "| %-100s |%n";
		String TABLE = "| %-4s| %-5s| %-25.25s| %-10s| %-20s| %-10s| %15s|%n";
		String[] COLUMNS = { "S.NO", "ID", "SOLD BY", "NO.ITEMS", "DATE", "STATUS", "AMOUNT" };

		StringBuilder builder = new StringBuilder();
		String indication = getUserSession().getRole() == Role.CASHIER ? "YOUR" : "THE";
		String greet = "HELLO, " + getUserSession().getFullName().toUpperCase() + " !" + " FIND ALL " + indication
				+ " TRANSACTIONS.";

		builder.append(String.format(LINE, "").replace(' ', '-'));
		builder.append(String.format(GREAT, greet));
		builder.append(String.format(LINE, "").replace(' ', '-'));
		builder.append(String.format(TABLE, COLUMNS));
		builder.append(String.format(LINE, "").replace(' ', '-'));

		int count = 1;
		Double totalAmount = Double.valueOf(0);
		for (Transaction transaction : transactions) {
			builder.append(String.format(TABLE, count, transaction.getId(), transaction.getSoldBy().getFullName(),
					transaction.getTransactionItems().size(), DateUtil.toString(transaction.getTransactionOn()),
					transaction.getType(), transaction.getTotalAmount()));
			count++;
			totalAmount += transaction.getTotalAmount();
		}
		builder.append(String.format(LINE, "").replace(' ', '-'));
		builder.append(String.format("| TOTAL AMOUNT: %87s|%n", totalAmount));
		builder.append(String.format(LINE, "").replace(' ', '-'));
		return builder.toString();
	}

	protected String generateTransactionItemsTable(Transaction transaction) throws Exception {
		StringBuilder builder = new StringBuilder();

		User currentUser = transaction.getSoldBy();
		String fullName = currentUser.getFullName();
		String name = currentUser.getRole().name();
		String time = DateUtil.toString(transaction.getTransactionOn());

		String LINE = "| %85s |%n";
		String HEANDER = "| NAME: %-62.62s %10s |%n";
		String HEADER_2 = "| ROLE: %-41.41s DATE: %20.20s %10.10s |%n";
		String TABLE = "| %-5s| %-5s| %-20.20s| %-15.15s| %7s|  %10s|  %10s|%n";
		String[] COLUMN = { "S.NO", "ID", "NAME", "SHORT NAME", "QTY", "PRICE", "AMOUNT" };

		builder.append(String.format(LINE, "").replace(' ', '-'));
		builder.append(String.format(HEANDER, fullName.toUpperCase(), "COUNTER NO: 0001"));
		builder.append(String.format(HEADER_2, name.toUpperCase(), time, "TYPE: " + transaction.getType().name()));

		builder.append(String.format(LINE, "").replace(' ', '-'));
		builder.append(String.format(TABLE, COLUMN));
		builder.append(String.format(LINE, "").replace(' ', '-'));

		int count = 1;
		for (TransactionItems items : transaction.getTransactionItems()) {
			Product product = items.getProduct();
			builder.append(String.format(TABLE, count, product.getId(), product.getFullName(), product.getShortName(),
					items.getQuantity(), product.getSellingPrice(), items.getTotalAmount()));
			count++;
		}
		builder.append(String.format(LINE, "").replace(' ', '-'));
		return builder.toString();
	}
}
