package com.tdam2013.grupo13.messaging;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by npnavarr on 17/07/2016.
 */
public class WebMessageReciverService extends IntentService {

    protected Logger logger;
    private SharedPreferences sharedPreferences;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public WebMessageReciverService() {
        super("WebMessageReciverService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        WebMessageClientReciver messageReciver = new WebMessageClientReciver(context);
        String userName = sharedPreferences.getString("user_name_pref", "");
        String password = sharedPreferences.getString("password_pref", "");

        try {
            while(true){
                Thread.sleep(6000);
                String timestamp = android.text.format.DateFormat.format("dd/MM/yyyy HH:mm:ss", new java.util.Date()).toString();
                String[] params = {userName,password,timestamp};
                String[] result = messageReciver.execute(params);
                if(result[0].equals("success")){

                    Log.e("Pepe",result[0]);
                    ArrayList nodes = messageReciver.getNodes();
                    if(!nodes.isEmpty()){
                        Log.e("MENSAJES DEVUELTOS","TENEMOS MENSAJES EN EL ARRAY");
                    }
                    //sendBroadcast(new Intent("nuevo intent"));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
