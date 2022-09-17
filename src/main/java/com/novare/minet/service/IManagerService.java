package com.novare.minet.service;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Order;
import com.novare.minet.model.Transaction;

public interface IManagerService extends IBaseService {

	Order vieworderList(Order order);

	Inventory reviewInventory(Inventory inventory);

	Transaction saveTransactions(Transaction transaction);

}
