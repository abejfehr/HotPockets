package com.spark.hotpockets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by megboudreau on 15-06-17.
 */


public class HotPocketMain extends ActionBarActivity {

    public static double lat;
    public static double lng;

    private ListView locationListView;

    private String selectedWord = null;


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
        registerForContextMenu(locationListView);

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
        registerForContextMenu(locationListView);

        Log.i("HOT_POCKETS", "I am in onRestart()");
        stopService(new Intent(getApplicationContext(), HotPocketScout.class));
        Intent startIntent = new Intent(getApplicationContext(), HotPocketScout.class);
        startIntent.setAction(getString(R.string.START_SCOUT_ACTION));
        startService(startIntent);

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
        registerForContextMenu(locationListView);

        Log.i("HOT_POCKETS", "I am in onResume()");
        stopService(new Intent(getApplicationContext(), HotPocketScout.class));
        Intent startIntent = new Intent(getApplicationContext(), HotPocketScout.class);
        startIntent.setAction(getString(R.string.START_SCOUT_ACTION));
        startService(startIntent);

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
        registerForContextMenu(locationListView);

        Log.i("HOT_POCKETS", "I am in onStart()");
        stopService(new Intent(getApplicationContext(), HotPocketScout.class));
        Intent startIntent = new Intent(getApplicationContext(), HotPocketScout.class);
        startIntent.setAction(getString(R.string.START_SCOUT_ACTION));
        startService(startIntent);

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        selectedWord = ((TextView) info.targetView).getText().toString();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_contextual_menu, menu);

        Log.i("HOT_POCKETS", "the selected word is: " + selectedWord);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final DatabaseManager dbMan = new DatabaseManager(getApplicationContext());
        Log.i("HOT_POCKETS", item.toString());
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit_name:


                // Creating the EditText for the dialog
                final EditText newName = new EditText(this);
                newName.setText(selectedWord);

                // Build the dialog
                new AlertDialog.Builder(this)
                        .setTitle("Edit Name")
                        .setMessage("Enter the new name for your Hot Pocket")
                        .setView(newName)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbMan.editHotPocket(selectedWord, newName.getText().toString());
                                onResume();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();



                return true;
            case R.id.delete_location:
                Log.i("HOT_POCKETS", "the selected nickname is: " + selectedWord);
                dbMan.removeHotPocket(selectedWord);
                onResume();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}
