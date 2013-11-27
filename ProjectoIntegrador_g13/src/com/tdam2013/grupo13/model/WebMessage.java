package com.tdam2013.grupo13.model;

public class WebMessage {

	private String message;
	private String dateTime;
	private String receiverName;

	public WebMessage(String dateTime, String message, String receiverName){
		this.dateTime = dateTime;
		this.message = message;
		this.receiverName = receiverName;
	}

	public WebMessage(String dateTime, String message){
		this.dateTime = dateTime;
		this.message = message;		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}		
}
