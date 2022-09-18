package com.novare.minet.service;

import java.util.List;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Product;

public interface IProductService extends IBaseService {

	String STORAGE = "assets/products.json";

	Product create(Product product) throws Exception;

	Inventory create(Inventory inventory) throws Exception;

	Product update(Product product) throws Exception;

	Product delete(Product product) throws Exception;

	Product findByShortName(String shortName) throws Exception;

	List<Product> find(String search) throws Exception;

	Inventory delete(Inventory inventory) throws Exception;

	List<Inventory> getAll() throws Exception;

}
