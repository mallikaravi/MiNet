package com.novare.minet.model;

import java.util.List;

public class Product {
	private int id;
	private String fullName;
	private String shortName;
	private String description;
	private double sellingPrice;
	private double costPrice;
	private int minQty;
	private List<ProductHistory> histories;

}
