package com.novare.minet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product extends IdProperty {
	private String fullName;
	private String shortName;
	private String description;
	private double sellingPrice;
	private double costPrice;
	private int minQty;
	private List<ProductHistory> histories = new ArrayList<>();

	/**
	 * 
	 */
	public Product() {
		super();
	}

	/**
	 * @param fullName
	 * @param shortName
	 * @param description
	 * @param sellingPrice
	 * @param costPrice
	 * @param minQty
	 */
	public Product(String fullName, String shortName, String description, double sellingPrice, double costPrice,
			int minQty) {
		super();
		this.fullName = fullName;
		this.shortName = shortName;
		this.description = description;
		this.sellingPrice = sellingPrice;
		this.costPrice = costPrice;
		this.minQty = minQty;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the sellingPrice
	 */
	public double getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * @return the costPrice
	 */
	public double getCostPrice() {
		return costPrice;
	}

	/**
	 * @param costPrice the costPrice to set
	 */
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * @return the minQty
	 */
	public int getMinQty() {
		return minQty;
	}

	/**
	 * @param minQty the minQty to set
	 */
	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}

	/**
	 * @return the histories
	 */
	public List<ProductHistory> getHistories() {
		return histories;
	}

	/**
	 * @param histories the histories to set
	 */
	public void setHistories(ProductHistory history) {
		getHistories().add(history);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		return Objects.hash(costPrice, description, fullName, histories, getId(), minQty, sellingPrice, shortName);
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
		Product other = (Product) obj;
		return Double.doubleToLongBits(costPrice) == Double.doubleToLongBits(other.costPrice)
				&& Objects.equals(description, other.description) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(histories, other.histories) && getId() == other.getId() && minQty == other.minQty
				&& Double.doubleToLongBits(sellingPrice) == Double.doubleToLongBits(other.sellingPrice)
				&& Objects.equals(shortName, other.shortName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Product [id=" + getId() + ", fullName=" + fullName + ", shortName=" + shortName + ", description="
				+ description + ", sellingPrice=" + sellingPrice + ", costPrice=" + costPrice + ", minQty=" + minQty
				+ ", histories=" + histories + "]";
	}

}
