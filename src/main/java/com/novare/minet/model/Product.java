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

	public Product() {
		super();
	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getMinQty() {
		return minQty;
	}

	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}

	public List<ProductHistory> getHistories() {
		return histories;
	}

	public void addHistories(User user) {
		ProductHistory history = new ProductHistory(DateUtil.toDate(LocalDateTime.now()), this, user);
		history.generateId();
		getHistories().add(history);
	}

	public Supplier getDefaultSupplier() {
		return defaultSupplier;
	}

	public void setDefaultSupplier(Supplier defaultSupplier) {
		this.defaultSupplier = defaultSupplier;
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
		Product other = (Product) obj;
		return getId() == other.getId();
	}

	@Override
	public String toString() {
		return fullName.toUpperCase() + "[" + shortName.toUpperCase() + "]";
	}

}
