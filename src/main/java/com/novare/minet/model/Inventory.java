package com.novare.minet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventory {
	private int id;
	private Product product;
	private double quantity;
	private double orderedQty;
	private List<InventoryHistory> histories = new ArrayList<>();

	public Inventory() {
	}

	/**
	 * @param product
	 * @param quantity
	 * @param orderedQty
	 * @param histories
	 */
	public Inventory(Product product, double quantity, double orderedQty) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.orderedQty = orderedQty;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the orderedQty
	 */
	public double getOrderedQty() {
		return orderedQty;
	}

	/**
	 * @param orderedQty the orderedQty to set
	 */
	public void setOrderedQty(double orderedQty) {
		this.orderedQty = orderedQty;
	}

	/**
	 * @return the histories
	 */
	public List<InventoryHistory> getHistories() {
		return histories;
	}

	/**
	 * @param histories the histories to set
	 */
	public void addHistories(InventoryHistory history) {
		getHistories().add(history);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@Override
	public int hashCode() {
		return Objects.hash(histories, id, orderedQty, product, quantity);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return Objects.equals(histories, other.histories) && id == other.id
				&& Double.doubleToLongBits(orderedQty) == Double.doubleToLongBits(other.orderedQty)
				&& Objects.equals(product, other.product)
				&& Double.doubleToLongBits(quantity) == Double.doubleToLongBits(other.quantity);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", product=" + product + ", quantity=" + quantity + ", orderedQty=" + orderedQty
				+ ", histories=" + histories + "]";
	}

}
