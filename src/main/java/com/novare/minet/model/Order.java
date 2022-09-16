package com.novare.minet.model;

import java.util.List;

public class Order {
	private int id;
	private Product product;
	private float quantity;
	private float totalAmount;
	private Supplier supplier;
	private List<OrderHistory> histories; 

}
