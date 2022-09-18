package com.novare.minet.service;

import java.util.List;

import com.novare.minet.model.Transaction;

public interface ITransactionService extends IBaseService {

	String STORAGE = "assets/transactions.json";

	Transaction create(Transaction transaction) throws Exception;

	List<Transaction> findByType(String type) throws Exception;

	List<Transaction> findById(int transactionId) throws Exception;

	List<Transaction> getAll() throws Exception;
}
