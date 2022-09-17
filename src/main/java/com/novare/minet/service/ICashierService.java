package com.novare.minet.service;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Order;
import com.novare.minet.model.Product;
import com.novare.minet.model.Transaction;

public interface ICashierService extends IBaseService {

	Inventory inventoryStatus(Inventory inventory);

	Transaction myTransactions(Transaction transaction);

	Transaction searchTransaction(Transaction transaction);

	Transaction deleteTransaction(Transaction transaction);

	Order myOrders(Order order);

	Product counterSale(Product product);

	Product returnProduct(Product product);
}
