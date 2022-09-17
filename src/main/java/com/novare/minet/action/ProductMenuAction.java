package com.novare.minet.action;

import com.novare.minet.controller.AdminController;
import com.novare.minet.model.User;
import com.novare.minet.service.IAdminService;
import com.novare.minet.serviceimpl.AdminServiceImpl;
import com.novare.minet.util.MenuContext;
import com.novare.minet.view.ProductView;

public class ProductMenuAction extends BaseMenuAction {

	public ProductMenuAction(MenuContext context, User currentUser) throws Exception {
		super(context, currentUser);
		String title = "";
		switch (context) {
		case CREATE_PRODUCT -> title = "Create Product Option :";
		case PRODUCT_LIST -> title = "Product List option : :";
		case EDIT_PRODUCT -> title = "Edit Product Option :";
		case DELETE_PRODUCT -> title = "Delete Product Option :";
		case SEARCH_PRODUCT -> title = "Search product Option :";

		}
		ProductView view = new ProductView(title);
		IProductService model = new ProductServiceImpl();
		ProductController controller = new ProductController(model, view);
		controller.setMenuVisible(context == MenuContext.PRODUCT);
		controller.requestUserInput(context, currentUser);

	}
}
