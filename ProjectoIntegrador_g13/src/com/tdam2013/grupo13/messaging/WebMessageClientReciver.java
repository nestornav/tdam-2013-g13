package com.tdam2013.grupo13.messaging;

import android.content.Context;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;
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

    private ArrayList results;


    public WebMessageClientReciver(Context context) {
        super(context);
        results = new ArrayList();
        this.logger = Logger.getLogger(getClass().getName());
    }

    @Override
    public String getRequest(String... params) {
        return String.format(GET_MSG_TEMPLATE, params);
    }

    @Override
    public String[] parseResult(SAXParser parser, InputSource is) throws SAXException, IOException {
        ResultHandler handler = new ResultHandler();
        Log.e("INPUTSOURCE",is.toString());
        parser.parse(is, handler);
        results = handler.getNodes();
        return handler.getResult();
    }

    public ArrayList<Message> getNodes(){
        return results;
    }

    class ResultHandler extends DefaultHandler {

        String[] res = new String[1];
        ArrayList<Message> messagesArray = new ArrayList<>();
        Message message;
        boolean messageFlag = false;

        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException{
            super.startElement(uri, localName, qName, attributes);

            if(localName.equalsIgnoreCase("result")){
                res[0] = attributes.getValue("type");
            }else if(localName.equalsIgnoreCase("message")){
                Log.e("MENSAJES",attributes.getValue("from") + " TIMESTAMP: "+ attributes.getValue("timestamp"));
                messageFlag = true;
                message = new Message();
                message.setFrom(attributes.getValue("from"));
                message.setTimestamp(attributes.getValue("timestamp"));
            }
        }

        public void characters(char ch[], int start, int length)
                throws SAXException {
            if(messageFlag){
                message.setMessage(new String(ch));
                messagesArray.add(message);
            }
        }

        public void endElement(String uri, String localName, String qName)
                throws SAXException {
           if(localName.equalsIgnoreCase("message")){
               messageFlag = false;
           }
        }

        public String[] getResult(){
            return res;
        }

        public ArrayList<Message> getNodes(){
            return messagesArray;
        }
    }

    class Message{
        public String from;
        public String timestamp;
        public String message;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
