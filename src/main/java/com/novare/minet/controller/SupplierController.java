package com.novare.minet.controller;

import com.novare.minet.service.ISupplierService;
import com.novare.minet.view.SupplierView;

public class SupplierController extends BaseController {

	public SupplierController(ISupplierService model, SupplierView view) {
		super(model, view);
	}

	@Override
	public ISupplierService getModel() {
		return (ISupplierService) super.getModel();
	}

	@Override
	public SupplierView getView() {
		return (SupplierView) super.getView();
	} 
	
}
