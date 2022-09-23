package com.novare.minet.model;

import java.util.Objects;

import com.novare.minet.util.Ids;

public class TransactionItems extends IdProperty {
	private Product product;
	private Transaction transaction;
	private float quantity;
	private double totalAmount;

	public TransactionItems() {
		super();
	}

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

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionItems other = (TransactionItems) obj;
		return getId() == other.getId();
	}

	@Override
	public String toString() {
		return "TransactionItems [id=" + getId() + ", product=" + product + ", quantity=" + quantity + ", totalAmount="
				+ totalAmount + "]";
	}

}
