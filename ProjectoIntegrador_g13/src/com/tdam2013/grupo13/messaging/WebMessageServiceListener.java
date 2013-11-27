package com.tdam2013.grupo13.messaging;

public interface WebMessageServiceListener {

	public void onMessageSent(String message, String time);
	
	public void onMessageError(String message, String time);
}
