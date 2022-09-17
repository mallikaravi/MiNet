package com.novare.minet.model;

import java.util.Date;
import java.util.Objects;

public class ProductHistory extends IdProperty {
	private Date updatedOn;
	private Product product;
	private User updateBy;

	/**
	 * 
	 */
	public ProductHistory() {
		super();
	}

	/**
	 * @param updatedOn
	 * @param product
	 * @param updateBy
	 */
	public ProductHistory(Date updatedOn, Product product, User updateBy) {
		super();
		this.updatedOn = updatedOn;
		this.product = product;
		this.updateBy = updateBy;
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
		return Objects.hash(getId(), product, updateBy, updatedOn);
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
		ProductHistory other = (ProductHistory) obj;
		return getId() == other.getId() && Objects.equals(product, other.product)
				&& Objects.equals(updateBy, other.updateBy) && Objects.equals(updatedOn, other.updatedOn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "ProductHistory [id=" + getId() + ", updatedOn=" + updatedOn + ", product=" + product + ", updateBy="
				+ updateBy + "]";
	}

}
