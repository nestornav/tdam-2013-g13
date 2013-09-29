package com.tdam2013.grupo13.adapters;

public class ConnectionHistory {
		
	private String date;
	private ConnectionHistoryStatus status;
	
	public ConnectionHistory(ConnectionHistoryStatus status,String date) {
		this.status = status;
		this.date = date;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public ConnectionHistoryStatus getConnectionStatus(){
		return status;
	}
	
	public void setConnectionStatus(ConnectionHistoryStatus status){
		this.status = status;
	}
	
	public enum ConnectionHistoryStatus{
		CONNECTED,LOST;
	}
}
