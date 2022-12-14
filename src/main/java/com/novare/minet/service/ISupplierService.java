package com.novare.minet.service;

import java.util.List;

import com.novare.minet.model.Supplier;

public interface ISupplierService extends IMiNetService {

	Supplier create(Supplier supplier) throws Exception;

	Supplier update(Supplier supplier) throws Exception;

	Supplier delete(Supplier supplier) throws Exception;

	List<Supplier> find(String search) throws Exception;

}
