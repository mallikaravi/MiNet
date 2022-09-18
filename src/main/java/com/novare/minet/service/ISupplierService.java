package com.novare.minet.service;

import com.novare.minet.model.Supplier;

public interface ISupplierService extends IBaseService {
	String STORAGE = "assets/suppliers.json";

	Supplier create(Supplier supplier) throws Exception;

	Supplier update(Supplier supplier) throws Exception;

	Supplier delete(Supplier supplier) throws Exception;

}
