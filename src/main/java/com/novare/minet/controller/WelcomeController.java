package com.novare.minet.controller;

import com.novare.minet.service.IWelcomeService;
import com.novare.minet.view.WelcomeView;

public class WelcomeController extends BaseController {

	public WelcomeController(IWelcomeService model, WelcomeView view) {
		super(model, view);
	}

	@Override
	public IWelcomeService getModel() {
		return (IWelcomeService) super.getModel();
	}

	@Override
	public WelcomeView getView() {
		return (WelcomeView) super.getView();
	}
	
}
