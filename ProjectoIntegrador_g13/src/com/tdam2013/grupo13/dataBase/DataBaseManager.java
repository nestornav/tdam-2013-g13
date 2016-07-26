package com.tdam2013.grupo13.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseManager extends SQLiteOpenHelper {

	private static String dbName = "DBG13";
	
	public DataBaseManager(Context context){
		super(context,dbName,null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE WebUser ("+
					"idWebUser INTEGER PRIMARY KEY , "+
					"userName TEXT);");
	
		db.execSQL("CREATE TABLE WebMassage ("+
					"idWebMessage INTEGER PRIMARY KEY , "+
					"senderName TEXT , "+
					"receiverName TEXT , "+
					"date TEXT , "+
					"message TEXT);");
		
		db.execSQL("CREATE TABLE ConnectivityLog ("+
					"idConnectivity INTEGER PRIMARY KEY , "+
					"date TEXT , "+
					"connectivityType TEXT , "+					
					"state TEXT);"); 		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS WebUser");
        db.execSQL("DROP TABLE IF EXISTS WebMessage");
        db.execSQL("DROP TABLE IF EXISTS ConnectivityLog");
        onCreate(db);		
	}
	
	public boolean insertConnectivityLog(String date, String connectivityType, String state){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		try{
			cv.put("date",date);
			cv.put("connectivityType",connectivityType);
			cv.put("state",state);
			
			db.insert("ConnectivityLog", "idConnectivity", cv);		
			db.close();
			return true;
		}catch(Exception e){
			db.close();
			return false;
		}finally{
			if(db != null) db.close();
		}		
	}
	
	public boolean insertWebClient(String nickname){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();		
		try{
			cv.put("userName",nickname);
			
			db.insert("WebUser", "idWebUser", cv);		
			db.close();	
			return true;
		}catch(Exception e){
			db.close();
			return false;
		}finally{
			if(db != null) db.close();
		}		
	}
	
	public boolean insertNewMessage(String senderName, String receiverName,String date, String message){		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		try{
			cv.put("senderName",senderName);
			cv.put("receiverName",receiverName);
			cv.put("date",date);
			cv.put("message",message);
			
			db.insert("WebMassage", "idWebMessage", cv);		
			db.close();
			return true;
		}catch(Exception e){
			db.close();
			return false;
		}finally{
			if(db != null) db.close();
		}	
	}	

	public Cursor getConnectivityLog(){		
		SQLiteDatabase db = this.getReadableDatabase();
		
		try{
			String query = "SELECT date, connectivityType, state FROM ConnectivityLog";
			Cursor c = db.rawQuery(query,null);	
			return c;
		}catch(Exception e){			
			return null;
		}
	}
	
	public Cursor getMessageUser(String userId){		
		SQLiteDatabase db = this.getReadableDatabase();
		try{
			String query = "SELECT receiverName, date, message FROM WebMassage WHERE senderName = '"+ userId+"' OR receiverName = '"+ userId+"'";
			Cursor c = db.rawQuery(query,null);
			return c;
		}catch(Exception e){
			db.close();
			return null;
		}		
	}
	
	public Cursor getAllMessage(){		
		SQLiteDatabase db = this.getReadableDatabase();
		try{
			String query = "SELECT message, date,receiverName FROM WebMassage";
			Cursor c = db.rawQuery(query,null);			
			return c;
		}catch(Exception e){
			db.close();
			return null;
		}		
	}
}
