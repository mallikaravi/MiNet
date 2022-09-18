package com.novare.minet.controller;

import java.util.List;

import com.novare.minet.model.Product;
import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;
import com.novare.minet.service.IBaseService;
import com.novare.minet.service.IProductService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.BaseView;
import com.novare.minet.view.ProductView;

public class ProductController extends BaseController {

	private Product newProduct = new Product();

	public ProductController(IBaseService model, BaseView view) {
		super(model, view);
	}

	@Override
	public IProductService getModel() {
		return (IProductService) super.getModel();
	}

	@Override
	public ProductView getView() {
		return (ProductView) super.getView();
	}

	public void requestUserInput(MenuContext context, User currentUser) throws Exception {
		try {
			super.requestUserInput(context, currentUser);
			int selectedOption = 0;
			switch (context) {
			case CREATE_PRODUCT -> createProduct();
			case PRODUCT_LIST -> productList();
			case EDIT_PRODUCT -> editProduct();
			case DELETE_PRODUCT -> deleteProduct();
			case SEARCH_PRODUCT -> searchProduct();
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

	private void searchProduct() throws Exception {
		String askSearch = getView().askSearch();
		List<Product> find = getModel().find(askSearch);
		if (find.isEmpty()) {
			getView().displayResultNotFound();
			return;
		} else {
			getView().setMenuOptions(find, false);
		}
		getView().waitForDecision();
	}

	private void editProduct() throws Exception {
		List<Product> productList = getModel().getAll();
		if (productList == null || productList.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}

		int selection = getView().askForEdit(productList);
		if (selection > -1) {
			Product selectedProduct = productList.get(selection);

			if (!getView().askProductShortName().isEmpty()) {
				selectedProduct.setShortName(getView().askProductShortName());
			}
			if (!getView().askProductDescription().isEmpty()) {
				selectedProduct.setDescription(getView().askProductDescription());
			}
			selectedProduct.setMinQty(getView().askProductMinQty());
			selectedProduct.setSellingPrice(getView().askProductSellingPrice());
			selectedProduct.setCostPrice(getView().askProductCostPrice());
			List<Supplier> allSuppliers = getModel().getAllSuppliers();
			if (allSuppliers == null || allSuppliers.isEmpty()) {
				// TODO FIXME should be deleted
				Supplier supplier = new Supplier("Gelaxy", "UK", "abc@delete.com", "1235");
				supplier.generateId();
				allSuppliers = List.of(supplier);
			}

			selection = getView().askDefaultSupplier(allSuppliers);
			selectedProduct.setDefaultSupplier(allSuppliers.get(selection));
			getModel().update(selectedProduct);
			getView().printSaveMessage();
			getView().waitForDecision();

		}

	}

	private void createProduct() throws Exception {
		if (isNull(newProduct.getFullName())) {
			newProduct.setFullName(getView().askProductFullName());
		}
		if (isNull(newProduct.getShortName())) {
			newProduct.setShortName(getView().askProductShortName());
		}
		if (isNull(newProduct.getDescription())) {
			newProduct.setDescription(getView().askProductDescription());
		}
		if (isNull(newProduct.getMinQty())) {
			newProduct.setMinQty(getView().askProductMinQty());
		}
		if (isNull(newProduct.getSellingPrice())) {
			newProduct.setSellingPrice(getView().askProductSellingPrice());
		}
		if (isNull(newProduct.getCostPrice())) {
			newProduct.setCostPrice(getView().askProductCostPrice());
		}
		if (isNull(newProduct.getDefaultSupplier())) {
			List<Supplier> allSuppliers = getModel().getAllSuppliers();
			if (allSuppliers == null || allSuppliers.isEmpty()) {
				// TODO FIXME should be deleted
				Supplier supplier = new Supplier("Gelaxy", "UK", "abc@delete.com", "1235");
				supplier.generateId();
				allSuppliers = List.of(supplier);
			}
			int selection = getView().askDefaultSupplier(allSuppliers);
			newProduct.setDefaultSupplier(allSuppliers.get(selection));
		}
		getModel().create(newProduct);
		getView().printSaveMessage();
		getView().waitForDecision();
	}

	private void productList() throws Exception {
		List<Product> productList = getModel().getAll();
		if (productList == null || productList.isEmpty()) {
			getView().displayResultNotFound();
		} else {
			getView().setMenuOptions(productList, false);
		}
		getView().waitForDecision();

	}

	private void deleteProduct() throws Exception {

		List<Product> productList = getModel().getAll();
		if (productList == null || productList.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}

		int selection = getView().askForDelete(productList);
		if (selection > -1) {
			Product selectedProduct = productList.get(selection);
			getModel().delete(selectedProduct);
			getView().printSaveMessage();
			getView().waitForDecision();

		}

	}

}
