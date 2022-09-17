package com.novare.minet.model;

import java.util.Date;
import java.util.Objects;

import com.novare.minet.util.Ids;

public class OrderHistory extends IdProperty {
	public enum OrderStatus {
		PENDING, WAITING, DELIVERED
	}
	private Order order;
	private Date updatedOn;
	private OrderStatus status;
	private User updateBy;

	/**
	 * 
	 */
	public OrderHistory() {
		super();
		Ids id = Ids.get();
		setId(id.getOrderHistoryId());
		id.close();
	
	}

	/**
	 * @param order
	 * @param updatedOn
	 * @param status
	 * @param updateBy
	 */
	public OrderHistory(Order order, Date updatedOn, OrderStatus status, User updateBy) {
		this();
		this.order = order;
		this.updatedOn = updatedOn;
		this.status = status;
		this.updateBy = updateBy;
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
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
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
		return Objects.hash(getId(), order, status, updateBy, updatedOn);
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
		return getId() == other.getId()  && Objects.equals(order, other.order) && status == other.status
				&& Objects.equals(updateBy, other.updateBy) && Objects.equals(updatedOn, other.updatedOn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "OrderHistory [id=" + getId() + ", order=" + order + ", updatedOn=" + updatedOn + ", status=" + status
				+ ", updateBy=" + updateBy + "]";
	}

}
