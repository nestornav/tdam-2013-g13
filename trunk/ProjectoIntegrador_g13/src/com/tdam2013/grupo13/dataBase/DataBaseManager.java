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
	
	public boolean insertConnectivityLog(int date, String connectivityType, String state){
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
		}		
	}
	
	public boolean insertNewMessage(String senderName, String receiverName,int date, String message){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		try{
			cv.put("senderName",senderName);
			cv.put("receiverName",receiverName);
			cv.put("date",date);
			cv.put("message",message);
			
			db.insert("WebMessage", "idWebMessage", cv);		
			db.close();
			return true;
		}catch(Exception e){
			db.close();
			return false;
		}		
	}	

	public Cursor getConnectivityLog(){		
		SQLiteDatabase db = getReadableDatabase();
		try{
			String query = "SELECT date, connectivityType, state FROM ConnectivityLog";
			Cursor c = db.rawQuery(query,null);
			db.close();
			return c;
		}catch(Exception e){
			db.close();
			return null;
		}		
	}
	
	public Cursor getMessageUser(String userId){		
		SQLiteDatabase db = getReadableDatabase();
		try{
			String query = "SELECT receiverName, date, message FROM WebMessage WHERE senderName ="+ userId;
			Cursor c = db.rawQuery(query,new String[]{userId});
			db.close();
			return c;
		}catch(Exception e){
			db.close();
			return null;
		}		
	}
}
