package com.twentyfivesquares.moviebrowser.manager;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.twentyfivesquares.moviebrowser.db.MovieDbHelper;

/**
 * All common manager work is done here. It's not so useful with only one table, but I left it
 *  in to get the idea out there.
 */
public class Manager {

    protected Context context;
    protected MovieDbHelper helper;

    public Manager(Context context) {
        this.context = context;
        this.helper = new MovieDbHelper(context);
    }

    protected SQLiteDatabase getReadableDatabase() {
        return helper.getReadableDatabase();
    }

    protected SQLiteDatabase getWriteableDatabase() {
        return helper.getWritableDatabase();
    }
}
