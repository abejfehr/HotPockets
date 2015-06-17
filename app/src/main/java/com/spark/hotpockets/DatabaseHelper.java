package com.spark.hotpockets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by abe on 15-06-17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private String DATABASE_CREATE;

    Context context;

    public DatabaseHelper(Context context) {
        super(context, context.getString(R.string.DB_NAME), null, DATABASE_VERSION);
        this.context = context;
        this.DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " +
                context.getString(R.string.DB_TABLE) + " (" +
                context.getString(R.string.DB_ROW_NICKNAME) + " TEXT, " +
                context.getString(R.string.DB_ROW_LAT) + " REAL, " +
                context.getString(R.string.DB_ROW_LONG) +
                " REAL, PRIMARY KEY (nickname, lat, lng));";
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.i(context.getString(R.string.HOT_POCKETS), DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(DatabaseManager.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + context.getString(R.string.DB_TABLE));
        onCreate(database);
    }}
