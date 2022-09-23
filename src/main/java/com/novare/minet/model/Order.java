package com.novare.minet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.novare.minet.util.DateUtil;
import com.novare.minet.util.Ids;

public class Order extends IdProperty {
	private Product product;
	private Double quantity;
	private Double totalAmount;
	private Supplier supplier;
	private OrderStatus status;
	private User createdBy;
	private List<OrderHistory> histories = new ArrayList<>();

	public Order() {
		super();
	}

	public Order(Product product, Double quantity, Double totalAmount, Supplier supplier, OrderStatus status,
			User createdBy) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.supplier = supplier;
		this.status = status;
		this.createdBy = createdBy;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int orderId = id.getOrderId();
		setId(orderId);
		id.close();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderHistory> getHistories() {
		return histories;
	}

	public void addHistories(User user) {
		OrderHistory history = new OrderHistory(DateUtil.toDate(LocalDateTime.now()), this, user);
		history.generateId();
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
		Order other = (Order) obj;
		return getId() == other.getId();
	}

	@Override
	public String toString() {
		return "Order [id=" + getId() + ", Status=" + status + "]";
	}

}
