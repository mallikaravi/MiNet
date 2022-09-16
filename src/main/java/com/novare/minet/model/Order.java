package com.novare.minet.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	List<Product> products = new ArrayList<Product>();
	private int id;

	public Order(List<Product> products, int id) {
		super();
		this.products = products;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Order [products=" + products + ", id=" + id + "]";
	}

}
