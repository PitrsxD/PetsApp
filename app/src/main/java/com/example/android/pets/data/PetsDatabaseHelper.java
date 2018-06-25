package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.pets.data.PetsContract.FeedEntry;

public class PetsDatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "pets.db";

    public static final String INTEGER = " INTEGER";
    public static final String TEXT = " TEXT";
    public static final String PRIMARY_KEY = " PRIMARY KEY";
    public static final String NOT_NULL = " NOT NULL";
    public static final String AUTOINCREMENT = " AUTOINCREMENT";
    public static final String DEFAULT = " DEFAULT";
    public static final String COMMA = ",";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE pets(" +
            FeedEntry._ID + INTEGER + PRIMARY_KEY + AUTOINCREMENT + COMMA +
            FeedEntry.COLUMN_PET_NAME + TEXT + NOT_NULL + COMMA +
            FeedEntry.COLUMN_PET_BREED + TEXT + COMMA +
            FeedEntry.COLUMN_PET_GENDER + INTEGER + NOT_NULL + COMMA +
            FeedEntry.COLUMN_PET_WEIGHT + INTEGER + DEFAULT + " 0"+
            ")";

    private  static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public PetsDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
