package com.novare.minet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.novare.minet.util.Ids;

public class Order extends IdProperty {
	private Product product;
	private float quantity;
	private float totalAmount;
	private Supplier supplier;
	private List<OrderHistory> histories = new ArrayList<>();

	public Order() {
		super();
		Ids id = Ids.get();
		setId(id.getOrderId());
		id.close();
	}

	/**
	 * @param product
	 * @param quantity
	 * @param totalAmount
	 * @param supplier
	 * @param histories
	 */
	public Order(Product product, float quantity, float totalAmount, Supplier supplier) {
		this();
		this.product = product;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.supplier = supplier;
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
	public float getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the histories
	 */
	public List<OrderHistory> getHistories() {
		return histories;
	}

	/**
	 * @param histories the histories to set
	 */
	public void addHistories(OrderHistory history) {
		getHistories().add(history);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		return Objects.hash(histories, getId(), product, quantity, supplier, totalAmount);
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
		Order other = (Order) obj;
		return Objects.equals(histories, other.histories) && getId() == other.getId()
				&& Objects.equals(product, other.product)
				&& Float.floatToIntBits(quantity) == Float.floatToIntBits(other.quantity)
				&& Objects.equals(supplier, other.supplier)
				&& Float.floatToIntBits(totalAmount) == Float.floatToIntBits(other.totalAmount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Order [id=" + getId() + ", product=" + product + ", quantity=" + quantity + ", totalAmount="
				+ totalAmount + ", supplier=" + supplier + ", histories=" + histories + "]";
	}

}
