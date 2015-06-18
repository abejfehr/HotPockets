package com.spark.hotpockets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by megboudreau on 15-06-17.
 */
public class DatabaseManager {

    private Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    DatabaseManager(Context context) {
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
        this.db = dbHelper.getWritableDatabase();

    }

    public void addHotPocket(HotPocket hotPocket) {
        ContentValues values = new ContentValues();
        values.put(context.getString(R.string.DB_ROW_LONG), hotPocket.getLong());
        values.put(context.getString(R.string.DB_ROW_LAT), hotPocket.getLat());
        values.put(context.getString(R.string.DB_ROW_NICKNAME), hotPocket.getNickname());
        db.insert(context.getString(R.string.DB_TABLE),
                null,
                values);
    }

    public void editHotPocket(String oldNickname, String newNickname) {
        // Simple way to edit a row
        String editSQL = "UPDATE " +
                context.getString(R.string.DB_TABLE) + " SET " +
                context.getString(R.string.DB_ROW_NICKNAME) + " = " + newNickname + " WHERE " +
                context.getString(R.string.DB_ROW_NICKNAME) + " = " + oldNickname;
        db.execSQL(editSQL);

    }

    public void removeHotPocket(String nickname) {
        db.delete(context.getString(R.string.DB_TABLE),
                context.getString(R.string.DB_ROW_NICKNAME) + "='" + nickname + "'",
                null);
    }

    public ArrayList<HotPocket> getAllHotPockets() {
        // Create the cursor thingy
        Cursor  cursor = db.rawQuery("SELECT * FROM " + context.getString(R.string.DB_TABLE), null);

        // Create a list to return later
        ArrayList<HotPocket> list = new ArrayList<>();

        // Start from the beginning
        if (cursor .moveToFirst()) {

            while (!cursor.isAfterLast()) {
                String nickname = cursor.getString(cursor
                        .getColumnIndex(context.getString(R.string.DB_ROW_NICKNAME)));
                double lat = cursor.getDouble(cursor
                        .getColumnIndex(context.getString(R.string.DB_ROW_LAT)));
                double lng = cursor.getDouble(cursor
                        .getColumnIndex(context.getString(R.string.DB_ROW_LONG)));

                list.add(new HotPocket(nickname, lat, lng));
                cursor.moveToNext();
            }
        }

        return list;
    }
}
