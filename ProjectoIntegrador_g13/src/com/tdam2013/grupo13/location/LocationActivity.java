package com.tdam2013.grupo13.location;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by npnavarr on 19/07/2016.
 */
public class LocationActivity extends Activity {

    private LocationService locationService;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        locationService = new LocationService(this);
    }

    @Override
    protected  void onStart(){
        super.onStart();
        if(!locationService.hasLocationEnabled()){
            locationService.openDeviceSettings(this);
        }
        locationService.startLocationUpdate();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Enviando mensaje");
        pDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Start the updates
        locationService.startLocationUpdate();
        Location loc = locationService.getLocation();
        while(loc == null){
            try {
                Thread.sleep(1000);
                loc = locationService.getLocation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.e("LOCATION latitud:",String.valueOf(loc.getLatitude())+" .... Longitud"+String.valueOf(loc.getLongitude()));
        Intent result = new Intent();
        result.putExtra("latitud",String.valueOf(loc.getLatitude()));
        result.putExtra("longitud",String.valueOf(loc.getLongitude()));
        setResult(Activity.RESULT_OK,result);
        finish();

    }

    @Override
    protected void onStop() {
        super.onStop();
        pDialog.dismiss();
        // Stop the updates
        locationService.stopLocationUpdate();
    }

}
