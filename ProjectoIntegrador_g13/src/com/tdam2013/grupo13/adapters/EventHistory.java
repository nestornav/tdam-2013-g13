package com.tdam2013.grupo13.adapters;

import com.tdam2013.grupo13.R;

public class EventHistory {

	private String dateTime;
	private String contact;
	private HistoryEventType type;
	
	public EventHistory(String dateTime, String contact, HistoryEventType type) {
		super();
		this.dateTime = dateTime;
		this.contact = contact;
		this.type = type;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public HistoryEventType getType() {
		return type;
	}

	public void setType(HistoryEventType type) {
		this.type = type;
	}

	public enum HistoryEventType {
		CALL(R.drawable.call_ico), SMS(R.drawable.sms_ico), EMAIL(R.drawable.mail_ico), WEB_MESSAGE(R.drawable.web_ico);
		
		HistoryEventType(int imageID){
			this.imageID = imageID;
		}

		private int imageID;
		
		public int getImageID() {
			return this.imageID;
		}
	}

	public int getImageID() {
		return this.type.getImageID();
	}
}
