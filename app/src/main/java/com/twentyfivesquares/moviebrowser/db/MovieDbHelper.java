package com.twentyfivesquares.moviebrowser.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.twentyfivesquares.moviebrowser.db.DatabaseConstants.DATABASE_NAME;
import static com.twentyfivesquares.moviebrowser.db.DatabaseConstants.DATABASE_VERSION;

public class MovieDbHelper extends SQLiteOpenHelper {

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseConstants.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}
