package com.novare.minet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.novare.minet.util.DateUtil;
import com.novare.minet.util.Ids;

public class Inventory extends IdProperty {

	private Product product;
	private double availQty;
	private double orderedQty;
	private List<InventoryHistory> histories = new ArrayList<>();

	public Inventory() {
		super();
	}

	/**
	 * @param product
	 * @param quantity
	 * @param orderedQty
	 * @param histories
	 */
	public Inventory(Product product, double availQty, double orderedQty) {
		this();
		this.product = product;
		this.availQty = availQty;
		this.orderedQty = orderedQty;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int inventoryId = id.getInventoryId();
		setId(inventoryId);
		id.close();
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
	 * @return the availQty
	 */
	public double getAvailQty() {
		return availQty;
	}

	/**
	 * @param availQty the availQty to set
	 */
	public void setAvailQty(double availQty) {
		this.availQty = availQty;
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
	public void addHistories(User user) {
		InventoryHistory history = new InventoryHistory(DateUtil.toDate(LocalDateTime.now()), this, user);
		history.generateId();
		getHistories().add(history);
		getHistories().add(history);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		return Objects.hash(getId());
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
		Inventory other = (Inventory) obj;
		return getId() == other.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Inventory [id=" + getId() + ", product=" + product + ", quantity=" + availQty + ", orderedQty="
				+ orderedQty + ", histories=" + histories + "]";
	}

}
