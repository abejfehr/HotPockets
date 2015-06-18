package com.spark.hotpockets;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class AddLocationActivity extends ActionBarActivity implements OnMapReadyCallback {

    private Button submit;
    private EditText nicknameEditText;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbManager = new DatabaseManager(getApplicationContext());

        //Make the 'Submit' button add the location to the database
        nicknameEditText = (EditText)findViewById(R.id.nickname);
        submit = (Button)this.findViewById(R.id.submitButton);
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(nicknameEditText.getText().toString().matches("")) {
                            noNicknameAddress();
                        }else{
                            //Toast argument
                            Context context = getApplicationContext();
                            int duration = Toast.LENGTH_SHORT;
                            String nickname = nicknameEditText.getText().toString();
                            String added = " Added!";
                            String text =  nickname + added;

                            //Show toast when location has been submitted
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }

                    }
                }
        );

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.return_home) {
            finish();
            finishActivity(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void noNicknameAddress(){

        Log.i(getString(R.string.HOT_POCKETS), "No Nickname Entered");

        //Error Toast
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        String text = "Enter a Nickname!";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(getString(R.string.HOT_POCKETS), "The map fragment is ready for use");
        googleMap.setMyLocationEnabled(true);

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(HotPocketMain.lat,
                        HotPocketMain.lng));
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(15);

        Log.i(getString(R.string.HOT_POCKETS), "Your current location according to this app is " + HotPocketMain.lat + ", " + HotPocketMain.lng);

                googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);

    }
}
