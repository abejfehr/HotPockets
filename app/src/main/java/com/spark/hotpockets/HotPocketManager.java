package com.spark.hotpockets;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class HotPocketManager extends ActionBarActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_pocket_manager);

        // Instantiate the database manager
        Context context = getApplicationContext();
        DatabaseManager dbManager = new DatabaseManager(context);
        db = dbManager.getWritableDatabase();

        // Start the Hot Pocket Scout if it's not already started
        Intent i = new Intent(context, HotPocketScout.class);
        context.startService(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hot_pocket_manager, menu);
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
