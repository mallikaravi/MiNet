package com.novare.minet.model;

import java.util.Date;
import java.util.Objects;

import com.novare.minet.util.Ids;

public class OrderHistory extends IdProperty {

	private Order order;
	private Date updatedOn;
	private User updateBy;

	public OrderHistory() {
		super();
	}

	public OrderHistory(Date updatedOn, Order order, User updateBy) {
		this();
		this.order = order;
		this.updatedOn = updatedOn;
		this.updateBy = updateBy;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int orderHistoryId = id.getOrderHistoryId();
		setId(orderHistoryId);
		id.close();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
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
		OrderHistory other = (OrderHistory) obj;
		return getId() == other.getId();
	}

	@Override
	public String toString() {
		return "OrderHistory [id=" + getId() + ", updatedOn=" + updatedOn + ", updateBy=" + updateBy + "]";
	}

}
