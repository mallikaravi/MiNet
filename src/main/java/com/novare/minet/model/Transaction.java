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

	public Transaction() {
		super();
	}

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

	public Date getTransactionOn() {
		return transactionOn;
	}

	/**
	 * @param transactionOn the transactionOn to set
	 */
	public void setTransactionOn(Date transactionOn) {
		this.transactionOn = transactionOn;
	}

	public List<TransactionItems> getTransactionItems() {
		return transactionItems;
	}

	public void addTransactionItems(TransactionItems transactionItems) {
		getTransactionItems().add(transactionItems);
	}

	public User getSoldBy() {
		return soldBy;
	}

	public void setSoldBy(User soldBy) {
		this.soldBy = soldBy;
	}
	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
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
		Transaction other = (Transaction) obj;
		return getId() == other.getId();
	}

	@Override
	public String toString() {
		return "Transaction [id=" + getId() + ", transactionOn=" + transactionOn + ", transactionItems="
				+ transactionItems + ", soldBy=" + soldBy + ", type=" + type + ", totalAmount=" + totalAmount + "]";
	}

}
