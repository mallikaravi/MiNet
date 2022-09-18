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

public class TransactionController extends BaseController {
	Transaction newTransaction = new Transaction(getUserSession(), TransactionType.SALE);

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
			getView().printUserRequest();
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
			getView().setMenuOptions(find, false);
		}
		getView().waitForDecision();
	}

	private void createTransaction() throws Exception {
		buildTransaction();

		newTransaction.setSoldBy(getUserSession());
		getModel().create(newTransaction);
		// Create Inventory
		for (TransactionItems item : newTransaction.getTransactionItems()) {
			Product product = item.getProduct();
			Inventory inventory = getModel().findInventoryByProductId(product.getId());
			inventory.setAvailQty(inventory.getAvailQty() - item.getQuantity());
			getModel().updateInventory(inventory);
		}
		getView().setMenuOptions(newTransaction.getTransactionItems(), false);
		getView().printSaveMessage();
		getView().waitForDecision();

	}

	private void buildTransaction() throws Exception {
		String askSearch = getView().askSearchProdWithNameOrId();
		List<Product> findByProductNameOrId = getModel().findByProductNameOrId(askSearch);
		if (findByProductNameOrId.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			createTransactionItem(findByProductNameOrId);
		}
		boolean more = getView().askMoreSale();
		if (more) {
			buildTransaction();
		}
	}

	private void createTransactionItem(List<Product> findByProductNameOrId) throws Exception {
		int selection = getView().askForChooseProduct(findByProductNameOrId);
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
