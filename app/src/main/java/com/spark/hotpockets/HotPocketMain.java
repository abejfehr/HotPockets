package com.spark.hotpockets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class HotPocketMain extends ActionBarActivity {

    public static double lat;
    public static double lng;

    private ListView locationListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_pocket_main);

        // Start the Hot Pocket Scout if it's not already started
        Intent startIntent = new Intent(getApplicationContext(), HotPocketScout.class);
        startIntent.setAction(getString(R.string.START_SCOUT_ACTION));
        startService(startIntent);

        //Create and populate Listview
        DatabaseManager dbMan = new DatabaseManager(getApplicationContext());
        locationListView = (ListView) findViewById(R.id.locationListView);
        ArrayList<HotPocket> hpListPopulants =  dbMan.getAllHotPockets();
        ArrayAdapter<HotPocket> adapter = new ArrayAdapter<HotPocket>(this,
                android.R.layout.simple_list_item_1, hpListPopulants);
        locationListView.setAdapter(adapter);

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        DatabaseManager dbMan = new DatabaseManager(getApplicationContext());
        locationListView = (ListView) findViewById(R.id.locationListView);
        ArrayList<HotPocket> hpListPopulants =  dbMan.getAllHotPockets();
        ArrayAdapter<HotPocket> adapter = new ArrayAdapter<HotPocket>(this,
                android.R.layout.simple_list_item_1, hpListPopulants);
        locationListView.setAdapter(adapter);

        Log.i("HOT_POCKETS", "I am in onRestart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        DatabaseManager dbMan = new DatabaseManager(getApplicationContext());
        locationListView = (ListView) findViewById(R.id.locationListView);
        ArrayList<HotPocket> hpListPopulants =  dbMan.getAllHotPockets();
        ArrayAdapter<HotPocket> adapter = new ArrayAdapter<HotPocket>(this,
                android.R.layout.simple_list_item_1, hpListPopulants);
        locationListView.setAdapter(adapter);

        Log.i("HOT_POCKETS", "I am in onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseManager dbMan = new DatabaseManager(getApplicationContext());
        locationListView = (ListView) findViewById(R.id.locationListView);
        ArrayList<HotPocket> hpListPopulants =  dbMan.getAllHotPockets();
        ArrayAdapter<HotPocket> adapter = new ArrayAdapter<HotPocket>(this,
                android.R.layout.simple_list_item_1, hpListPopulants);
        locationListView.setAdapter(adapter);

        Log.i("HOT_POCKETS", "I am in onStart()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hot_pocket_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;

        int id = item.getItemId();

        //Open new Activity if 'plus' button is pressed on Action Bar
        if (id == R.id.button_add) {
            intent = new Intent(getApplicationContext(), AddLocationActivity.class);
            startActivityForResult(intent, 0);
            //mode.finish(); // Action picked, so close the CAB
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
