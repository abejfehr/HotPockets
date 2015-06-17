package com.spark.hotpockets;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by abe on 15-06-17.
 */
public class HotPocketScout extends android.app.Service implements LocationListener {

    // TODO: Use these values in "production"
    //private static final long MIN_TIME_INTERVAL = 1000 * 60 * 3; // Three minutes
    //private static final long MIN_DIST_INTERVAL = 50; // Fifty metres

    private static final long MIN_TIME_INTERVAL = 1000 * 30; // Thirty seconds
    private static final long MIN_DIST_INTERVAL = 10; // Thirty seconds

    private LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // Acquire a reference to the system Location Manager
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
    - get the list of hotpockets from the dbmanager
    - check if current location is in a hotpocket

     */

    public void checkIfInHotPocket(Location location) {
        // Get the hotpocket locations from the database manager
        // Loop through them and determine whether or not we're near any of the hot pockets
        // TODO: Make it do what the comments above say to do. This is currently for testing only

        // Create a sample location for testing (Somewhere near Arnprior, ON)
        Location hotPocketTestLocation = new Location("");
        hotPocketTestLocation.setLatitude(45.3373744d);
        hotPocketTestLocation.setLongitude(-76.2781496d);
        
        WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        float distance = location.distanceTo(hotPocketTestLocation);
        if(distance > 200) {

            // Get the WiFi state to check if we're already connected to a WiFi network(and don't disconnect if we are)
            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if(wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED && !mWifi.isConnected()) {
                // Turn off the WiFi
                wifiManager.setWifiEnabled(false);
                CharSequence text = "Disabling the WiFi connection";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Log.i(getString(R.string.HOT_POCKETS), "LatLng X is " + distance + " meters away...disabling the WiFi connection");
            }
        } else {
            if(wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
                // Turn on the WiFi
                wifiManager.setWifiEnabled(true);
                CharSequence text = "Enabling the WiFi connection";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Log.i(getString(R.string.HOT_POCKETS), "LatLng X is " + distance + " meters away...enabling the WiFi connection");
            }
        }

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
