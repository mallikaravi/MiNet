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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getAvailQty() {
		return availQty;
	}

	public void setAvailQty(double availQty) {
		this.availQty = availQty;
	}

	public double getOrderedQty() {
		return orderedQty;
	}

	public void setOrderedQty(double orderedQty) {
		this.orderedQty = orderedQty;
	}

	public List<InventoryHistory> getHistories() {
		return histories;
	}

	public void addHistories(User user) {
		InventoryHistory history = new InventoryHistory(DateUtil.toDate(LocalDateTime.now()), this, user);
		history.generateId();
		getHistories().add(history);
		getHistories().add(history);
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
		Inventory other = (Inventory) obj;
		return getId() == other.getId();
	}

	@Override
	public String toString() {
		return product.toString();
	}

}
