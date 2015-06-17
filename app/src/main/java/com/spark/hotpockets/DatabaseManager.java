package com.spark.hotpockets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by megboudreau on 15-06-17.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    static final String dbName="hotPocketDB";
    static final String locationTable="Locations";
    static final String colAddress="Address";
    static final String colWifi="Wifi";
    static final String colLat="Latitude";
    static final String colLong="Longitude";

    static final String viewLoc="viewLocations";

    public DatabaseManager(Context context) {
        super(context, dbName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE "+locationTable+
        "("+colAddress+" TEXT PRIMARY KEY, "+
        colWifi+" TEXT, "+colLat+" Integer, "+colLong+" Integer,);");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+locationTable);

    }
}
