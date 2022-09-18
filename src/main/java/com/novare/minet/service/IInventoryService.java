package com.novare.minet.service;

import java.util.List;

import com.novare.minet.model.Inventory;

public interface IInventoryService extends IBaseService {

	String STORAGE = "assets/inventory.json";

	Inventory create(Inventory inventory) throws Exception;

	Inventory update(Inventory inventory) throws Exception;

	Inventory delete(Inventory inventory) throws Exception;

	List<Inventory> getAll() throws Exception;
}
