package com.spark.hotpockets;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by abe on 15-06-17.
 */
public class HotPocketScout extends android.app.Service implements LocationListener {

    private static final long MIN_TIME_INTERVAL = 1000 * 60 * 3; // Three minutes
    private static final long MIN_DIST_INTERVAL = 50; // Fifty metres

    private LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // Acquire a reference to the system Location Manager
        Log.i(HotPocketConstants.HOT_POCKET, Context.LOCATION_SERVICE);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start polling for location
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_INTERVAL, MIN_DIST_INTERVAL, this);

        Context context = getApplicationContext();
        CharSequence text = "The location service is now started!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        return super.onStartCommand(intent, flags, startId);
    }

    /*

    Things we need to do
    - check for location - onLocationChanged
    - get the list of hotpockets from the dbmanager
    - check if current location is in a hotpocket
    - disable or enable wifi based on that information

     */

    public void checkIfInHotPocket(Location location) {
        // Get the hotpocket locations from the database manager
        // Loop through them and determine whether or not we're near any of the hot pockets


        // TODO: Make it do what the comments above say to do. This is currently for testing only

        Context context = getApplicationContext();
        CharSequence text = "I just got an updated location!!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public void onLocationChanged(Location location) {
        // Called when a new location is found by the network location provider.
        checkIfInHotPocket(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
