package com.spark.hotpockets;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class HotPocketManager extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_pocket_manager);

        Log.i(HotPocketConstants.HOT_POCKET, "Achieved State 1");
        // Start the Hot Pocket Scout if it's not already started
        Context context = getApplicationContext();
        Log.i(HotPocketConstants.HOT_POCKET, "Achieved State 2");
        Intent i = new Intent(context, HotPocketScout.class);
        Log.i(HotPocketConstants.HOT_POCKET, "Achieved State 3");
        context.startService(i);
        Log.i(HotPocketConstants.HOT_POCKET, "Achieved State 4");
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
