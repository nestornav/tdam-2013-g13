package com.tdam2013.grupo13.messaging;

import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.content.Context;

public class WebMessageClientSender extends WebMessageClient {

	protected static final String SEND_MSG_TEMPLATE = "" +
			"<action id=\"REQUEST_RANDOM_VALUE\" name=\"send-message\">" +
				"<action-detail>" +
					"<auth username=\"%1$s\" key=\"%2$s\"></auth>" +
						"<message to=\"%3$s\">" +
							"<![CDATA[%4$s]]>" +
						"</message>" +
					"</action-detail>" +
			"</action>";
	
	public WebMessageClientSender(Context context) {
		super(context);
		this.logger = Logger.getLogger(getClass().getName());
	}

	@Override
	public String getRequest(String... params) {
		return String.format(SEND_MSG_TEMPLATE, params);
	}

	@Override
	public String[] parseResult(SAXParser parser, InputSource params)
			throws SAXException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
