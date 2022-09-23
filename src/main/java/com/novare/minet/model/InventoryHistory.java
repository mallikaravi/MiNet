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

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), inventory, updateBy, updatedOn);
	}

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

	@Override
	public String toString() {
		return "InventoryHistory [id=" + getId() + ", updatedOn=" + updatedOn + ", inventory=" + inventory
				+ ", updateBy=" + updateBy + "]";
	}

}
