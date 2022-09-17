package com.novare.minet.controller;

import com.novare.minet.model.User;
import com.novare.minet.service.IBaseService;
import com.novare.minet.service.IProductService;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.BaseView;
import com.novare.minet.view.ProductView;

public class ProductController extends BaseController {

	public ProductController(IBaseService model, BaseView view) {
		super(model, view);
		// TODO Auto-generated constructor stub
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

			default -> {
				selectedOption = getView().getUserInput();
			}
			}
			getModel().handleOption(selectedOption, getUserSession());
		} catch (Exception e) {
			getView().printInvalidOption();
			getView().printUserRequest();
			setMenuVisible(false);
			requestUserInput(context, currentUser);
		}
	}

	private Object searchProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object editProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object createProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object productList() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object deleteProduct() {
		// TODO Auto-generated method stub
		return null;
	}

}
