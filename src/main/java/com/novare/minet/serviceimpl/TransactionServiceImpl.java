package com.novare.minet.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.novare.minet.action.AdminMenuAction;
import com.novare.minet.action.CashierMenuAction;
import com.novare.minet.action.ManagerMenuAction;
import com.novare.minet.action.TransactionMenuAction;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.Transaction;
import com.novare.minet.model.User;
import com.novare.minet.service.ITransactionService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.util.ServiceUtil;

public class TransactionServiceImpl extends BaseServiceImpl implements ITransactionService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {

		switch (selectedOption) {
		case 0 -> {
			switch (currentUser.getRole()) {
			case ADMIN -> new AdminMenuAction(MenuContext.ADMIN, currentUser);
			case MANAGER -> new ManagerMenuAction(MenuContext.MANAGER, currentUser);
			case CASHIER -> new CashierMenuAction(MenuContext.CASHIER, currentUser);
			default -> new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
			}
		}
		case 1 -> {
			new TransactionMenuAction(MenuContext.CREATE, currentUser);
		}
		case 2 -> {
			new TransactionMenuAction(MenuContext.RETURN_PRODUCT, currentUser);
		}
		case 3 -> {
			new TransactionMenuAction(MenuContext.LIST, currentUser);
		}
		case 4 -> {
			new TransactionMenuAction(MenuContext.SEARCH, currentUser);
		}

		default -> throw new IndexOutOfBoundsException();
		}

	}

	@Override
	public Transaction create(Transaction transaction) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Transaction> transactions = ServiceUtil.loadModel(Transaction.class, STORAGE);
		transaction.generateId();
		transaction.calculateAmount();
		transactions.add(transaction);
		ServiceUtil.saveModel(transactions, STORAGE);
		return transaction;
	}

	@Override
	public List<Transaction> findByType(String tranType) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Transaction> transactions = ServiceUtil.loadModel(Transaction.class, STORAGE);
		List<Transaction> result = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if (transaction.getType().name().equals(tranType.toUpperCase())) {
				filterByUserRole(result, transaction);
			}
		}
		return result;
	}

	private void filterByUserRole(List<Transaction> result, Transaction transaction) {
		switch (getCurrentUser().getRole()) {
		case CASHIER: {
			boolean contains = transaction.getSoldBy().equals(getCurrentUser());
			if (contains) {
				result.add(transaction);
			}
		}
		default:
			result.add(transaction);
		}
	}

	@Override
	public List<Transaction> findById(int transactionId) throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Transaction> transactions = ServiceUtil.loadModel(Transaction.class, STORAGE);
		List<Transaction> result = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if (transaction.getId().equals(transactionId)) {
				switch (getCurrentUser().getRole()) {
				case CASHIER: {
					boolean contains = transaction.getSoldBy().equals(getCurrentUser());
					if (contains) {
						result.add(transaction);
					}
				}
				default:
					result.add(transaction);
				}
			}
		}
		return result;
	}

	@Override
	public List<Transaction> getAll() throws Exception {
		ServiceUtil.checkAssetFolder();
		List<Transaction> transactions = ServiceUtil.loadModel(Transaction.class, STORAGE);
		switch (getCurrentUser().getRole()) {
		case CASHIER -> {
			List<Transaction> result = new ArrayList<>();
			for (Transaction transaction : transactions) {
				boolean contains = transaction.getSoldBy().equals(getCurrentUser());
				if (contains) {
					result.add(transaction);
				}
			}
			return result;
		}
		default -> {
			return transactions;
		}
		}

	}

}
