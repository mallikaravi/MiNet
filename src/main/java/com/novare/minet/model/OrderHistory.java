package com.novare.minet.model;

import java.util.Date;

public class OrderHistory {
	public enum OrderStatus {
		PENDING, WAITING, DELIVERED
	}

	private int id;
	private Order order;
	private Date updatedOn;
	private OrderStatus status;
	private User updateBy;

}
