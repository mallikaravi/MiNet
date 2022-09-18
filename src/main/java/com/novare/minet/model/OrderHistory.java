package com.novare.minet.model;

import java.util.Date;
import java.util.Objects;

import com.novare.minet.util.Ids;

public class OrderHistory extends IdProperty {

	private Order order;
	private Date updatedOn;
	private User updateBy;

	/**
	 * 
	 */
	public OrderHistory() {
		super();
	}

	/**
	 * @param order
	 * @param updatedOn
	 * @param status
	 * @param updateBy
	 */
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

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the updateBy
	 */
	public User getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
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
		OrderHistory other = (OrderHistory) obj;
		return getId() == other.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "OrderHistory [id=" + getId() + ", order=" + order + ", updatedOn=" + updatedOn + ", updateBy="
				+ updateBy + "]";
	}

}
