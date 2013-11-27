package com.tdam2013.grupo13.utils;

import java.util.ArrayList;
import java.util.Date;

import com.tdam2013.grupo13.dataBase.DataBaseManager;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;



public class ActiveService extends Service {

	private static final int ACTIVE_SERVICE_ID = 1;

	private NetworkScanner _connectivityListener = new NetworkScanner();
	private NetworkConnectivtyHandler _connectivityHandler = new NetworkConnectivtyHandler();
	private DataBaseManager db;
	
	@Override
	public void onCreate() {
		super.onCreate();
		db = new DataBaseManager(ActiveService.this);		
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		System.out.println("ActiveStart.onStart!!");
		_connectivityListener.startListening(this);
		_connectivityListener.registerHandler(_connectivityHandler,
				ACTIVE_SERVICE_ID);
	}

	public void endApplication() {
		_connectivityListener.unregisterHandler(_connectivityHandler);
		_connectivityListener.stopListening();		
		stopSelf();
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		System.out.println("ActiveService.onRebind!!");
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("ActiveService.onDestroy!!");
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("ActiveService.onUnbind!!");
		return super.onUnbind(intent);
	}


	public class LocalBinder extends Binder {
		public ActiveService getService() {
			return ActiveService.this;
		}
	}

	// This is the object that receives interactions from clients. See
	// RemoteService for a more complete example.
	private final IBinder mBinder = new LocalBinder();

	public boolean isConnectedToNetwork() {
		return _connectivityListener.isConnected();
	}

	private class NetworkConnectivtyHandler extends Handler {

		private Boolean _connected;

		@Override
		public void handleMessage(Message msg) {
			boolean connected = _connectivityListener.isConnected();

			if (_connected == null || _connected != connected) {
				_connected = connected;
				if (connected) {					
					boolean result  = db.insertConnectivityLog(new Date().toGMTString(), "3G", "CONNECTED");
					Toast.makeText(getApplicationContext(), "Conectado"+result, Toast.LENGTH_SHORT).show();
				} else {
					Log.i("Connection status", "Connection Disconected");
					db.insertConnectivityLog(new Date().toGMTString(), "3G", "LOST");
					Toast.makeText(getApplicationContext(), "DESConectado", Toast.LENGTH_SHORT).show();
				}
				//Bundle data = new Bundle();
				//data.putBoolean(Constants.EXTRA_NETWORK_CONNECTED, connected);				
			}
			
			
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}






