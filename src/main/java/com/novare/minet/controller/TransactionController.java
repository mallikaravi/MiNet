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
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.BaseView;
import com.novare.minet.view.TransactionView;

public class TransactionController extends MiNetController {

	private Transaction newTransaction = new Transaction(getUserSession(), TransactionType.SALE);

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
			getView().printMessage(generateTransactionTable(transactions));
			boolean details=getView().askDisplay();
			if(details) {
				int selection = getView().askForChoose("", transactions);
				Transaction transaction = transactions.get(selection);
				getView().printMessage(generateTransactionItemsTable(transaction));
			}
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
			getView().printMessage(generateTransactionTable(find));
			boolean details=getView().askDisplay();
			if(details) {
				int selection = getView().askForChoose("", find);
				Transaction transaction = find.get(selection);
				getView().printMessage(generateTransactionItemsTable(transaction));
			}
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

			int selection = getView().askForChoose(generateTransactionItemsTable(transaction), transactionItems);
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

			getView().printReceipt(generatePrintReceipt(newTransaction));
		}
		getView().waitForDecision();
	}

	private void createTransaction() throws Exception {
		newTransaction.setSoldBy(getUserSession());
		buildTransaction(newTransaction);

		getModel().create(newTransaction);

		updateInventory(newTransaction);

		getView().printReceipt(generatePrintReceipt(newTransaction));
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

		String printProductDetails = generateProductTable(findByProductNameOrId);

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

}
