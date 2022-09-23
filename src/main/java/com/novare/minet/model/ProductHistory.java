package com.novare.minet.model;

import java.util.Date;
import java.util.Objects;

import com.novare.minet.util.Ids;

public class ProductHistory extends IdProperty {
	private Date updatedOn;
	private Product product;
	private User updateBy;

	public ProductHistory() {
		super();
	}

	public ProductHistory(Date updatedOn, Product product, User updateBy) {
		this();
		this.updatedOn = updatedOn;
		this.product = product;
		this.updateBy = updateBy;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int productHistoryId = id.getProductHistoryId();
		setId(productHistoryId);
		id.close();
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), product, updateBy, updatedOn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductHistory other = (ProductHistory) obj;
		return getId() == other.getId() && Objects.equals(product, other.product)
				&& Objects.equals(updateBy, other.updateBy) && Objects.equals(updatedOn, other.updatedOn);
	}

	@Override
	public String toString() {
		return "ProductHistory [id=" + getId() + ", updatedOn=" + updatedOn + ", product=" + product + ", updateBy="
				+ updateBy + "]";
	}

}
