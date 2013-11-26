package com.tdam2013.grupo13.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseManager extends SQLiteOpenHelper {

	public DataBaseManager(Context context){
		super(context,"DBG13",null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE WebUser ("+
					"idWebUser INTEGER PRIMARY KEY, "+
					"userName TEXT");
	
		db.execSQL("CREATE TABLE WebMassage ("+
					"idWebMessage INTEGER PRIMARY KEY, "+
					"senderName TEXT, "+
					"receiverName TEXT, "+
					"date INTEGER, "+
					"message TEXT");
		
		db.execSQL("CREATE TABLE ConnectivityLog ("+
					"idConnectivity INTEGER PRIMARY KEY, "+
					"date INTEGER, "+
					"connectivityType TEXT, "+					
					"state TEXT"); 		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS WebUser");
        db.execSQL("DROP TABLE IF EXISTS WebMessage");
        db.execSQL("DROP TABLE IF EXISTS ConnectivityLog");
        onCreate(db);		
	}
	
	public void insertConnectivityLog(int date, String connectivityType, String state){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put("date",date);
		cv.put("connectivityType",connectivityType);
		cv.put("state",state);
		
		db.insert("ConnectivityLog", "idConnectivity", cv);		
		db.close();
	}
	
	public void insertWebClient(String nickname){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put("userName",nickname);
		
		db.insert("WebUser", "idWebUser", cv);		
		db.close();
	}
	
	public void insertNewMessage(String senderName, String receiverName,int date, String message){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put("senderName",senderName);
		cv.put("receiverName",receiverName);
		cv.put("date",date);
		cv.put("message",message);
		
		db.insert("WebMessage", "idWebMessage", cv);		
		db.close();
	}	
}
