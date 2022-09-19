package com.novare.minet.controller;

import java.util.List;

import javax.naming.InsufficientResourcesException;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Product;
import com.novare.minet.model.Transaction;
import com.novare.minet.model.TransactionItems;
import com.novare.minet.model.TransactionType;
import com.novare.minet.model.User;
import com.novare.minet.service.ITransactionService;
import com.novare.minet.util.DateUtil;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;
import com.novare.minet.view.BaseView;
import com.novare.minet.view.TransactionView;

public class TransactionController extends MiNetController {

	public TransactionController(ITransactionService model, BaseView view) {
		super(model, view);
	}

	@Override
	public ITransactionService getModel() {
		return (ITransactionService) super.getModel();
	}

	@Override
	public TransactionView getView() {
		return (TransactionView) super.getView();
	}

	@Override
	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case CREATE -> createTransaction();
			case RETURN_PRODUCT -> performReturn();
			case SEARCH -> performSearch();
			case LIST -> displayTransactions();
			default -> {
				selectedOption = getView().getUserInput();
			}
			}
			getModel().handleOption(selectedOption, getUserSession());
		} catch (InsufficientResourcesException e) {
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		} catch (Exception e) {
			getView().printInvalidOption();
			getView().printUserRequest();
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		}
	}

	private void displayTransactions() throws Exception {
		List<Transaction> transactions = getModel().getAll();
		if (transactions == null || transactions.isEmpty()) {
			getView().displayResultNotFound();
		} else {
			getView().setMenuOptions(transactions, false);
		}
		getView().waitForDecision();

	}

	private void performSearch() throws Exception {
		int askSearch = getView().askSearchWithNumber();
		List<Transaction> find = getModel().findById(askSearch);
		if (find.isEmpty()) {
			getView().displayResultNotFound();
			return;
		} else {
			getView().setMenuOptions(find, false);
		}
		getView().waitForDecision();
	}

	private void performReturn() throws Exception {
		int askSearch = getView().askSearchWithNumber();
		List<Transaction> find = getModel().findById(askSearch);
		if (find.isEmpty()) {
			getView().displayResultNotFound();
			return;
		} else {
			Transaction transaction = find.get(0);
			List<TransactionItems> transactionItems = transaction.getTransactionItems();

			int selection = getView().askForChoose(buildTransactionTable(transaction), transactionItems);
			TransactionItems returnItem = transactionItems.get(selection);

			int quantity = getView().askProductQty();

			Product product = returnItem.getProduct();

			// Amend Transaction
			transaction.getTransactionItems().remove(returnItem);
			transaction.setType(TransactionType.AMEND);
			returnItem.setQuantity(returnItem.getQuantity() - quantity);
			returnItem.setTotalAmount(returnItem.getQuantity() * product.getSellingPrice());
			transaction.addTransactionItems(returnItem);

			// Return Transaction
			Transaction newTransaction = new Transaction(getUserSession(), TransactionType.RETURN);
			TransactionItems newItems = new TransactionItems(product, -quantity,
					-(quantity * product.getSellingPrice()));
			newItems.generateId();
			newTransaction.addTransactionItems(newItems);

			getModel().update(transaction);
			getModel().create(newTransaction);

			updateInventory(newTransaction);

			getView().printReceipt(getPrintReceipt(newTransaction));
		}
		getView().waitForDecision();
	}

	private void createTransaction() throws Exception {
		Transaction newTransaction = new Transaction(getUserSession(), TransactionType.SALE);

		buildTransaction(newTransaction);

		getModel().create(newTransaction);

		updateInventory(newTransaction);

		getView().printReceipt(getPrintReceipt(newTransaction));
//		getView().printSaveMessage();
		getView().waitForDecision();

	}

	private void updateInventory(Transaction newTransaction) throws Exception {
		// Update Inventory
		for (TransactionItems item : newTransaction.getTransactionItems()) {
			Product product = item.getProduct();
			Inventory inventory = getModel().findInventoryByProductId(product.getId());
			inventory.setAvailQty(inventory.getAvailQty() - item.getQuantity());
			getModel().updateInventory(inventory);
		}
	}

	private void buildTransaction(Transaction newTransaction) throws Exception {
		String askSearch = getView().askSearchProdWithNameOrId();
		List<Product> findByProductNameOrId = getModel().findByProductNameOrId(askSearch);
		if (findByProductNameOrId.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			createTransactionItem(newTransaction, findByProductNameOrId);
		}
		boolean more = getView().askMoreSale();
		if (more) {
			buildTransaction(newTransaction);
		}
	}

	private void createTransactionItem(Transaction newTransaction, List<Product> findByProductNameOrId)
			throws Exception {

		String printProductDetails = buildProductTable(findByProductNameOrId);

		int selection = getView().askForChoose(printProductDetails, findByProductNameOrId);
		Product product = findByProductNameOrId.get(selection);
		int quantity = getView().askProductQty();
		Inventory inventory = getModel().findInventoryByProductId(product.getId());
		if ((inventory.getAvailQty() - quantity) < product.getMinQty()) {
			getView().printMessage(
					product.getFullName().toUpperCase() + " minimum stock should be " + product.getMinQty() + "\n");
			throw new InsufficientResourcesException();
		} else {
			TransactionItems items = new TransactionItems(product, quantity, quantity * product.getSellingPrice());
			items.generateId();
			newTransaction.addTransactionItems(items);
		}
	}

	/************************************************************************
	 * Formating the text
	 ***********************************************************************/

	private String buildProductTable(List<Product> products) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));
		builder.append(String.format("| %-5s| %-5s| %-20s| %-15s| %10s|  %10s|  %7s|%n", "S.NO", "ID", "NAME",
				"SHORT NAME", "PRICE", "AVAIL QTY", "MIN QTY"));
		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));

		int count = 1;
		for (Product product : products) {
			Inventory inventory = getModel().findInventoryByProductId(product.getId());
			builder.append(String.format("| %-5s| %-5s| %-20s| %-15s| %10s|  %10s|  %7s|%n", count, product.getId(),
					product.getFullName(), product.getShortName(), product.getSellingPrice(), inventory.getAvailQty(),
					product.getMinQty()));
			count++;
		}
		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));
		return builder.toString();
	}

	private String getPrintReceipt(Transaction transaction) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append(ServiceUtil.printHeader(getUserSession()));

		builder.append("\n");
		builder.append(String.format(" %-30s | %5s | %10s | %15s %n", "ITEM NAME", "QTY", "PRICE", "AMOUNT"));
		builder.append(String.format(" %70s %n", "").replace(' ', '-'));
		for (TransactionItems items : transaction.getTransactionItems()) {
			Product product = items.getProduct();
			builder.append(String.format(" %-30s  %6s  %11s  %16s %n", product.getShortName(), items.getQuantity(),
					product.getSellingPrice(), items.getTotalAmount()));
		}
		builder.append("\n");
		builder.append(String.format(" %70s %n", "").replace(' ', '-'));
		builder.append(String.format(" TOTAL AMOUNT: %55s %n", transaction.getTotalAmount()));
		builder.append(String.format(" %70s %n", "").replace(' ', '-'));
		builder.append(String.format(" BALANCE: %60s %n", "0.0"));
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));
		builder.append(String.format(" %20s %-20s %10s %n", "", "THANK YOU COME AGAIN", ""));
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));

		return builder.toString();
	}

	private String buildTransactionTable(Transaction transaction) throws Exception {
		StringBuilder builder = new StringBuilder();

		User currentUser = transaction.getSoldBy();
		String fullName = currentUser.getFullName();
		String name = currentUser.getRole().name();
		String time = DateUtil.toString(transaction.getTransactionOn());

		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));
		builder.append(String.format("| NAME: %-62s %10s |%n", fullName.toUpperCase(), "COUNTER NO: 0001"));
		builder.append(String.format("| ROLE: %-41s DATE: %10s %10s |%n", name.toUpperCase(), time,
				"TYPE: "+transaction.getType().name()));

		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));
		builder.append(String.format("| %-5s| %-5s| %-20s| %-15s| %7s|  %10s|  %10s|%n", "S.NO", "ID", "NAME",
				"SHORT NAME", "QTY", "PRICE", "AMOUNT"));
		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));

		int count = 1;
		for (TransactionItems items : transaction.getTransactionItems()) {
			Product product = items.getProduct();
			builder.append(String.format("| %-5s| %-5s| %-20s| %-15s| %7s|  %10s|  %10s|%n", count, product.getId(),
					product.getFullName(), product.getShortName(), items.getQuantity(), product.getSellingPrice(),
					items.getTotalAmount()));
			count++;
		}
		builder.append(String.format("| %85s |%n", "").replace(' ', '-'));
		return builder.toString();
	}
}
