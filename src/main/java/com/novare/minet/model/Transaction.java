package com.novare.minet.model;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
	List<Product> products = new ArrayList<>();
	private int receiptno;
	
	public Transaction(List<Product> products, int receiptno) {
		super();
		this.products = products;
		this.receiptno = receiptno;
	}

	@Override
	public String toString() {
		return "Transaction [products=" + products + ", receiptno=" + receiptno + "]";
	}
	
	
	
}
