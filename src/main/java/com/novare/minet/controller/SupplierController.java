package com.novare.minet.controller;

import java.util.List;

import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;
import com.novare.minet.service.ISupplierService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.SupplierView;

public class SupplierController extends MiNetController {

	private Supplier newSupplier = new Supplier();

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

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case CREATE -> createSupplier();
			case EDIT -> editSupplier();
			case DELETE -> deleteSupplier();
			case LIST -> supplierList();
			case SEARCH -> searchSupplier();
			default -> selectedOption = getView().getUserInput();
			}
			getModel().handleOption(selectedOption, getUserSession());
		} catch (Exception e) {
			getView().printInvalidOption();
			getView().printUserRequest();
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		}
	}

	private void searchSupplier() throws Exception {
		String askSearch = getView().askSearch();
		List<Supplier> findSupplier = getModel().find(askSearch);
		if (findSupplier.isEmpty()) {
			getView().displayResultNotFound();
			return;
		} else {
			getView().printMessage(generateSupplierTable(findSupplier));
		}
		getView().waitForDecision();
	}

	private void createSupplier() throws Exception {
		if (isNull(newSupplier.getName())) {
			newSupplier.setName(getView().askSupplierName());
		}
		if (isNull(newSupplier.getAddress())) {
			newSupplier.setAddress(getView().askSupplierAddress());
		}
		if (isNull(newSupplier.getEmail())) {
			newSupplier.setEmail(getView().askSupplierEmail());
		}
		if (isNull(newSupplier.getPhoneNumber())) {
			newSupplier.setPhoneNumber(getView().askSupplierPhoneNumber());
		}
		getModel().create(newSupplier);
		getView().printSaveMessage();
		getView().waitForDecision();
	}

	private void supplierList() throws Exception {
		List<Supplier> supplierList = getModel().getAllSuppliers();
		if (supplierList == null || supplierList.isEmpty()) {
			getView().displayResultNotFound();
		} else {
			getView().printMessage(generateSupplierTable(supplierList));
		}
		getView().waitForDecision();

	}

	private void deleteSupplier() throws Exception {
		List<Supplier> supplierList = getModel().getAllSuppliers();
		if (supplierList == null || supplierList.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}
		String supplierTable = generateSupplierTable(supplierList);
		int selection = getView().askForDelete(supplierTable, supplierList);
		if (selection > -1) {
			Supplier selectedSupplier = supplierList.get(selection);
			getModel().delete(selectedSupplier);
			getView().printSaveMessage();
			getView().waitForDecision();

		}

	}

	private void editSupplier() throws Exception {
		List<Supplier> supplierList = getModel().getAllSuppliers();
		if (supplierList == null || supplierList.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}
		int selection = getView().askForEdit(generateSupplierTable(supplierList), supplierList);
		if (selection > -1) {
			Supplier selectedSupplier = supplierList.get(selection);

			selectedSupplier.setName(getView().askSupplierName());
			selectedSupplier.setAddress(getView().askSupplierAddress());
			selectedSupplier.setEmail(getView().askSupplierEmail());
			selectedSupplier.setPhoneNumber(getView().askSupplierPhoneNumber());
			getModel().update(selectedSupplier);
			getView().printSaveMessage();
			getView().waitForDecision();

		}

	}

}
