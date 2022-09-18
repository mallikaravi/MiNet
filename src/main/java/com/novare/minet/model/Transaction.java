package com.novare.minet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.novare.minet.util.DateUtil;
import com.novare.minet.util.Ids;

public class Transaction extends IdProperty {

	private Date transactionOn;
	private List<TransactionItems> transactionItems = new ArrayList<>();
	private User soldBy;
	private TransactionType type;
	private Double totalAmount;

	/**
	 * 
	 */
	public Transaction() {
		super();
	}

	/**
	 * @param transactionOn
	 * @param soldBy
	 * @param type
	 * @param totalAmount
	 */
	public Transaction(User soldBy, TransactionType type) {
		this();
		this.transactionOn = DateUtil.toDate(LocalDateTime.now());
		this.soldBy = soldBy;
		this.type = type;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int transactionId = id.getTransactionId();
		setId(transactionId);
		id.close();
	}

	public void calculateAmount() {
		Double totalAmount = Double.valueOf(0);
		for (TransactionItems transactionItems : transactionItems) {
			totalAmount += transactionItems.getTotalAmount();
		}
		setTotalAmount(totalAmount);
	}

	/**
	 * @return the transactionOn
	 */
	public Date getTransactionOn() {
		return transactionOn;
	}

	/**
	 * @param transactionOn the transactionOn to set
	 */
	public void setTransactionOn(Date transactionOn) {
		this.transactionOn = transactionOn;
	}

	/**
	 * @return the transactionItems
	 */
	public List<TransactionItems> getTransactionItems() {
		return transactionItems;
	}

	/**
	 * @param transactionItems the transactionItems to set
	 */
	public void addTransactionItems(TransactionItems transactionItems) {
		getTransactionItems().add(transactionItems);
	}

	/**
	 * @return the soldBy
	 */
	public User getSoldBy() {
		return soldBy;
	}

	/**
	 * @param soldBy the soldBy to set
	 */
	public void setSoldBy(User soldBy) {
		this.soldBy = soldBy;
	}

	/**
	 * @return the type
	 */
	public TransactionType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TransactionType type) {
		this.type = type;
	}

	/**
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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
		Transaction other = (Transaction) obj;
		return getId() == other.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Transaction [id=" + getId() + ", transactionOn=" + transactionOn + ", transactionItems="
				+ transactionItems + ", soldBy=" + soldBy + ", type=" + type + ", totalAmount=" + totalAmount + "]";
	}

}
