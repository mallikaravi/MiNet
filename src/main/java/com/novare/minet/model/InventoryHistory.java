package com.novare.minet.model;

import java.util.Date;
import java.util.Objects;

import com.novare.minet.util.Ids;

public class InventoryHistory extends IdProperty {

	private Date updatedOn;
	private Inventory inventory;
	private User updateBy;

	public InventoryHistory() {
		super();
	}

	/**
	 * @param updatedOn
	 * @param inventory
	 * @param updateBy
	 */
	public InventoryHistory(Date updatedOn, Inventory inventory, User updateBy) {
		this();
		this.updatedOn = updatedOn;
		this.inventory = inventory;
		this.updateBy = updateBy;
	}

	@Override
	public void generateId() {
		super.generateId();
		Ids id = Ids.get();
		int inventoryHistoryId = id.getInventoryHistoryId();
		setId(inventoryHistoryId);
		id.close();
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
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
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
		return Objects.hash(getId(), inventory, updateBy, updatedOn);
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
		InventoryHistory other = (InventoryHistory) obj;
		return getId() == other.getId() && Objects.equals(inventory, other.inventory)
				&& Objects.equals(updateBy, other.updateBy) && Objects.equals(updatedOn, other.updatedOn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "InventoryHistory [id=" + getId() + ", updatedOn=" + updatedOn + ", inventory=" + inventory
				+ ", updateBy=" + updateBy + "]";
	}

}
