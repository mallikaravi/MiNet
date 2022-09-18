package com.novare.minet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.novare.minet.util.DateUtil;
import com.novare.minet.util.Ids;

public class Product extends IdProperty {
	private Integer id;
	private String fullName;
	private String shortName;
	private String description;
	private Double sellingPrice;
	private Double costPrice;
	private Integer minQty;
	private Supplier defaultSupplier;
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
	public Product(String fullName, String shortName, String description, Double sellingPrice, Double costPrice,
			Integer minQty) {
		this();
		this.fullName = fullName;
		this.shortName = shortName;
		this.description = description;
		this.sellingPrice = sellingPrice;
		this.costPrice = costPrice;
		this.minQty = minQty;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int productId = id.getProductId();
		setId(productId);
		id.close();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	public Double getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * @return the costPrice
	 */
	public Double getCostPrice() {
		return costPrice;
	}

	/**
	 * @param costPrice the costPrice to set
	 */
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * @return the minQty
	 */
	public Integer getMinQty() {
		return minQty;
	}

	/**
	 * @param minQty the minQty to set
	 */
	public void setMinQty(Integer minQty) {
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
	public void addHistories(User user) {
		ProductHistory history = new ProductHistory(DateUtil.toDate(LocalDateTime.now()), this, user);
		history.generateId();
		getHistories().add(history);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	/**
	 * @return the defaultSupplier
	 */
	public Supplier getDefaultSupplier() {
		return defaultSupplier;
	}

	/**
	 * @param defaultSupplier the defaultSupplier to set
	 */
	public void setDefaultSupplier(Supplier defaultSupplier) {
		this.defaultSupplier = defaultSupplier;
	}

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
		Product other = (Product) obj;
		return getId() == other.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return fullName.toUpperCase() + "[" + shortName.toUpperCase() + "]";
	}

}
