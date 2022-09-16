package com.novare.minet.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	
	List<Product> products=new ArrayList<>();

	public Inventory(List<Product> products) {
		super();
		this.products = products;
	}

	@Override
	public String toString() {
		return "Inventory [products=" + products + "]";
	}
	
	
}
