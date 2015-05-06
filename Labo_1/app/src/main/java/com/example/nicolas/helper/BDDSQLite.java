package com.example.nicolas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nicolas on 02/05/2015.
 */
public class BDDSQLite extends SQLiteOpenHelper {

    private static final String TABLE_CLUB = "clubs";

    private static final String REQ_CREATE_CLUB = "CREATE TABLE " + TABLE_CLUB +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " name TEXT NOT NULL," +
            " local TEXT NOT NULL," +
            " icon INTEGER NOT NULL," +
            " website TEXT NOT NULL);";

    public BDDSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQ_CREATE_CLUB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_CLUB + ";");
        onCreate(db);
    }
}
