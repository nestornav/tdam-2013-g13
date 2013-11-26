package com.tdam2013.grupo13.messaging;

import java.util.logging.Logger;

import android.content.Context;

public class WebMessageClientRegister extends WebMessageClient {

	public WebMessageClientRegister(Context context) {
		super(context);
		logger = Logger.getLogger(getClass().getName());
	}

	protected static final String REGISTER_USER_TEMPLATE = ""
			+ "<action id=\"REQUEST_RANDOM_VALUE\" name=\"register-user\">"
			+ "<action-detail>" + "<user username=\"%1$s\" password=\"%2$s\"/>"
			+ "</action-detail>" + "</action>";
	
	@Override
	public String getRequest(String... params) {
		return String.format(REGISTER_USER_TEMPLATE, params);
	}

	@Override
	public String[] parseResult(String params) {
		// TODO Auto-generated method stub
		return null;
	}

}
