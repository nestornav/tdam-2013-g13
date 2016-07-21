package com.tdam2013.grupo13.messaging;

import android.content.Context;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;

/**
 * Created by npnavarr on 17/07/2016.
 */
public class WebMessageClientReciver extends WebMessageClient {
    protected static final String GET_MSG_TEMPLATE = "" +
            "<action id=\"REQUEST_RANDOM_VALUE\" name=\"get-messages\">" +
            "<action-detail>" +
            "<auth username=\"%1$s\" key=\"%2$s\"></auth>" +
            "<filter type=\"timestamp\">%3$s</filter>"+
            "</action-detail>" +
            "</action>";



    public WebMessageClientReciver(Context context) {
        super(context);
        this.logger = Logger.getLogger(getClass().getName());
    }

    @Override
    public String getRequest(String... params) {
        return String.format(GET_MSG_TEMPLATE, params);
    }

    @Override
    public String[] parseResult(SAXParser parser, InputSource is) throws SAXException, IOException {
        ResultHandler handler = new ResultHandler();
        parser.parse(is, handler);

        return handler.getResult();

    }

    //	-Resultado OK <result id="REQUEST_RANDOM_VALUE" type="success"></result>
//	Ejemplo: <result type="success" id="REQUEST_RANDOM_VALUE"></result>
//	-Resultado ERROR
//	<result id="REQUEST_RANDOM_VALUE" type="error"><detail code="ERROR_CODE" description="ERROR_DESCRIPTION"/></result>
//	Ejemplo: <result type="error" id="REQUEST_RANDOM_VALUE"><detail description="wrong username and password combination" code="12"></detail></result>
    class ResultHandler extends DefaultHandler {

        String[] res = new String[1];

        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException{
            super.startElement(uri, localName, qName, attributes);

            if(localName.equalsIgnoreCase("result")){
                res[0] = attributes.getValue("type");
                res[1] = attributes.toString();
            }
        }

        public String[] getResult(){
            return res;
        }
    }
}
