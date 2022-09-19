package com.novare.minet.controller;

import java.util.List;

import com.novare.minet.model.Inventory;
import com.novare.minet.model.Product;
import com.novare.minet.model.Supplier;
import com.novare.minet.model.User;
import com.novare.minet.service.IMiNetService;
import com.novare.minet.service.IProductService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.BaseView;
import com.novare.minet.view.ProductView;

public class ProductController extends MiNetController {

	private Product newProduct = new Product();

	public ProductController(IMiNetService model, BaseView view) {
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
			case CREATE -> createProduct();
			case LIST -> productList();
			case EDIT -> editProduct();
			case DELETE -> deleteProduct();
			case SEARCH -> searchProduct();
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
		List<Product> find = getModel().findByProductNameOrId(askSearch);
		if (find.isEmpty()) {
			getView().displayResultNotFound();
			return;
		} else {
			getView().setMenuOptions(find, false);
		}
		getView().waitForDecision();
	}

	private void editProduct() throws Exception {
		List<Inventory> productList = getModel().getAllInventories();
		if (productList == null || productList.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}

		int selection = getView().askForEdit(productList);
		if (selection > -1) {
			Inventory inventory = productList.get(selection);
			Product selectedProduct = inventory.getProduct();

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
			selection = getView().askDefaultSupplier(allSuppliers);
			selectedProduct.setDefaultSupplier(allSuppliers.get(selection));

			Double availQty = getView().askProductAvailQty();
			inventory.setAvailQty(availQty);

			getModel().update(selectedProduct);

			// Delete Inventory
			getModel().updateInventory(inventory);

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
			int selection = getView().askDefaultSupplier(allSuppliers);
			newProduct.setDefaultSupplier(allSuppliers.get(selection));
		}
		Double availQty = getView().askProductAvailQty();
		getModel().create(newProduct);

		// Create Inventory
		Inventory inventory = new Inventory(newProduct, availQty, 0);
		getModel().createInventory(inventory);

		getView().printSaveMessage();
		getView().waitForDecision();
	}

	private void productList() throws Exception {
		List<Inventory> productList = getModel().getAllInventories();
		if (productList == null || productList.isEmpty()) {
			getView().displayResultNotFound();
		} else {
			getView().setMenuOptions(productList, false);
		}
		getView().waitForDecision();

	}

	private void deleteProduct() throws Exception {
		List<Inventory> productList = getModel().getAllInventories();
		if (productList == null || productList.isEmpty()) {
			getView().displayResultNotFound();
			getView().waitForDecision();
			return;
		}

		int selection = getView().askForDelete(productList);
		if (selection > -1) {
			Inventory inventory = productList.get(selection);
			Product selectedProduct = inventory.getProduct();

			getModel().delete(selectedProduct);
			// Delete Inventory
			getModel().deleteInventory(inventory);

			getView().printSaveMessage();
			getView().waitForDecision();

		}

	}

}
