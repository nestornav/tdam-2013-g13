package com.tdam2013.grupo13.dataBase;

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
}
