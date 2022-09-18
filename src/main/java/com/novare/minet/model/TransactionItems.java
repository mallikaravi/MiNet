package com.novare.minet.model;

import java.util.Objects;

import com.novare.minet.util.Ids;

public class TransactionItems extends IdProperty {
	private Product product;
	private Transaction transaction;
	private float quantity;
	private double totalAmount;

	/**
	 * 
	 */
	public TransactionItems() {
		super();
	}

	/**
	 * @param product
	 * @param quantity
	 * @param totalAmount
	 */
	public TransactionItems(Product product, float quantity, double totalAmount) {
		this();
		this.product = product;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int transactionItemId = id.getTransactionItemId();
		setId(transactionItemId);
		id.close();
	}

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
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
	public float getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		return Objects.hash(getId(), product, quantity, totalAmount);
	}
	/*
	 * (non-Javadoc)
	 * 
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
		TransactionItems other = (TransactionItems) obj;
		return getId() == other.getId() && Objects.equals(product, other.product)
				&& Float.floatToIntBits(quantity) == Float.floatToIntBits(other.quantity)
				&& Double.doubleToLongBits(totalAmount) == Double.doubleToLongBits(other.totalAmount);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "TransactionItems [id=" + getId() + ", product=" + product + ", quantity=" + quantity + ", totalAmount="
				+ totalAmount + "]";
	}

}
