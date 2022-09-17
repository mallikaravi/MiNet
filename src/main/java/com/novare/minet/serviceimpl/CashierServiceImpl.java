package com.novare.minet.serviceimpl;

import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.Inventory;
import com.novare.minet.model.Order;
import com.novare.minet.model.Product;
import com.novare.minet.model.Transaction;
import com.novare.minet.model.User;
import com.novare.minet.service.ICashierService;
import com.novare.minet.util.MenuContext;

public class CashierServiceImpl extends BaseServiceImpl implements ICashierService {

	@Override
	public void handleOption(int selectedOption, User currentUser) throws Exception {
		switch (selectedOption) {
			case 1 -> {
				new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
			}
			case 2 -> {
				new WelcomeMenuAction(MenuContext.INVENTORYSTATUS, currentUser);
			}
			case 3 -> {
				new WelcomeMenuAction(MenuContext.MYTRANSACTIONS, currentUser);

			}
			case 4 -> {
				new WelcomeMenuAction(MenuContext.MYORDERS, currentUser);

			}
			case 5 -> {
				new WelcomeMenuAction(MenuContext.COUNTERSALE, currentUser);

			}
			case 6 -> {
				new WelcomeMenuAction(MenuContext.RETURNPRODUCT, currentUser);

			}
			case 7 -> {
				new WelcomeMenuAction(MenuContext.SEARCHTRANSACTION, currentUser);

			}
			case 8 -> {
				new WelcomeMenuAction(MenuContext.DELETETRANSACTION, currentUser);

			}
			default -> throw new IndexOutOfBoundsException();
		}

	}

	@Override
	public Inventory inventoryStatus(Inventory inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction myTransactions(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order myOrders(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product counterSale(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction searchTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction deleteTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product returnProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
