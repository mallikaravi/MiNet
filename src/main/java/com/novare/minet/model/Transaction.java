package com.novare.minet.model;

import java.util.Date;
import java.util.List;

public class Transaction {
	private int id;
	private Date transactionOn;
	private List<TransactionItems> transactionItems;
	private User soldBy;
	private TransactionType type;
	private double totalAmount;

}
