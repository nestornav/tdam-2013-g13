package com.tdam2013.grupo13.messaging;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
	public String[] parseResult(SAXParser parser, InputSource is) throws SAXException, IOException {
		ResultHandler handler = new ResultHandler();
		parser.parse(is, handler);

		return handler.getResult();

	}

//	-Resultado OK:
//		<result id="REQUEST_RANDOM_VALUE" type="success"><user username="REQUESTED_USERNAME"/></result>
//		Ejemplo: <result type="success" id="REQUEST_RANDOM_VALUE"><user username="pablo1234"></user></result>
//		-Resultado ERROR
//		<result id="REQUEST_RANDOM_VALUE" type="error"><detail code="ERROR_CODE" description="ERROR_DESCRIPTION"/></result>
//		Ejemplo: <result type="error" id="REQUEST_RANDOM_VALUE"><detail code="5"></detail></result>

	class ResultHandler extends DefaultHandler{
		
		String[] res = new String[1];
		
		public void startElement(String uri, String localName, String qName,
                Attributes attributes) throws SAXException{
			super.startElement(uri, localName, qName, attributes);
			
			if(localName.equalsIgnoreCase("result")){
//				RegistrationErrorResult result = new RegistrationErrorResult();
//				result.setType(attributes.getValue("type"));
				res[0] = attributes.getValue("type");
			}
		}
		
		public String[] getResult(){
			return res;
		}
	}
}
