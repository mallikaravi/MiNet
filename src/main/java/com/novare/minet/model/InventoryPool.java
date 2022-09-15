package com.novare.minet.model;

import java.util.ArrayList;
import java.util.List;

public class InventoryPool {
	private List<InventoryItem> inventoryItem = new ArrayList<InventoryItem>();

	public InventoryPool(List<InventoryItem> inventoryItem) {
		super();
		this.inventoryItem = inventoryItem;
	}

	public List<InventoryItem> getInventoryItem() {
		return inventoryItem;
	}

	@Override
	public String toString() {
		return "InventoryPool [inventoryItem=" + inventoryItem + "]";
	}

}
