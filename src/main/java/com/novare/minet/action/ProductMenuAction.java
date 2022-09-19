package com.novare.minet.action;

import com.novare.minet.controller.ProductController;
import com.novare.minet.model.User;
import com.novare.minet.service.IProductService;
import com.novare.minet.serviceimpl.ProductServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.ProductView;

public class ProductMenuAction extends MiNetMenuAction {

	public ProductMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case CREATE -> title = "Create Product Options:";
		case LIST -> title = "Product List options:";
		case EDIT -> title = "Edit Product Options:";
		case DELETE -> title = "Delete Product Options:";
		case SEARCH -> title = "Search product Options:";
		default -> title = "Product Menu optionss:";

		}
		ProductView view = new ProductView(getAppHeader(),title);
		IProductService model = new ProductServiceImpl();
		ProductController controller = new ProductController(model, view);
		controller.setMenuVisible(context == MenuContext.PRODUCT);
		controller.requestUserInput(context, currentUser);

	}
}
