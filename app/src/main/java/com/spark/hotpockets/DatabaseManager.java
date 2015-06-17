package com.spark.hotpockets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
<<<<<<< Updated upstream
=======


>>>>>>> Stashed changes

/**
 * Created by megboudreau on 15-06-17.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "hotPocketDB";
    static final int DATABASE_VERSION = 1;

<<<<<<< Updated upstream
=======
    static final String viewLoc="viewLocations";
>>>>>>> Stashed changes
    Context context;

    public DatabaseManager(Context context) {
        super(context, dbName, null, 1);
<<<<<<< Updated upstream
        this.context = context;
        SQLiteDatabase myDB = SQLiteDatabase.openOrCreateDatabase("hpdb",  , null);
        create();
    }

    public void create(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE " + hotPocketsTable + "(" + colNickname + " TEXT, " +
                colLat + " REAL, " + colLong + " REAL, PRIMARY KEY (" + colNickname + ", " +
                colLat + ", " + colLong + "));";
        Log.i(context.getString(R.string.HOT_POCKETS), createSQL);
        db.execSQL(createSQL);
=======
        /*this.context = context;
        SQLiteDatabase db = getSQLiteDatabase();
        this.createDatabase(db);*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createStatement = "CREATE TABLE "+locationTable+
                "("+colAddress+" VARCHAR, "+
                colWifi+" VARCHAR, "+colLat+" INTEGER, "+colLong+
                " INTEGER, PRIMARY KEY (" + colLat
                + ", " + colLong + "));";
        Log.i(context.getString(R.string.HOT_POCKETS), createStatement);
        db.execSQL(createStatement);


>>>>>>> Stashed changes
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
