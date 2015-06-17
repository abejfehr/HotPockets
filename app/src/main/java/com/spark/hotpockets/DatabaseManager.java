package com.spark.hotpockets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by megboudreau on 15-06-17.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "hotPocketDB";
    static final int DATABASE_VERSION = 1;

    Context context;

    public DatabaseManager(Context context) {
        super(context, dbName, null, 1);
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
