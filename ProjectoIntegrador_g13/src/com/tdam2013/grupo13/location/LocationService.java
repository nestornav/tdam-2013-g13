package com.tdam2013.grupo13.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by npnavarr on 15/07/2016.
 */
public class LocationService {

    private static String SERVICE_PROVIDER = LocationManager.GPS_PROVIDER;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location myLocation;
    private Context context;

    public LocationService(Context context){
        this.context = context;
        //get the references to the location manager
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    /*Check if the gps provider is enabled*/
    public boolean hasLocationEnabled() {
        try {
             return locationManager.isProviderEnabled(SERVICE_PROVIDER);
        }
        catch (Exception e) {
            return false;
        }
    }

    /*If the location service is off this function startup the activity
    * for the location settings*/
    public void openDeviceSettings(Context context) {
        context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /*This function will create a new listener which
    * will be passed to the location manager to get the las location*/
    public void startLocationUpdate(){
        myLocation =  locationManager.getLastKnownLocation(SERVICE_PROVIDER);
        locationListener  = new LocationServiceListener();
        locationManager.requestLocationUpdates(SERVICE_PROVIDER,500,0,locationListener);
    }

    /*Stop the update of location from gps*/
    public void stopLocationUpdate(){
        if(locationListener != null){
            locationManager.removeUpdates(locationListener);
            locationListener = null;
        }
    }

    public Location getLocation(){
        return myLocation;
    }

    private void showLocation(Location location){
        myLocation = location;
    }

    private class LocationServiceListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
            Log.e("DESDE EL SERVICIO",String.valueOf(location.getLatitude())+","+String.valueOf(location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            //MENSAJE TOAST QUE SE ENCENDIO EL GPS
        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
