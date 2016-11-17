package com.twentyfivesquares.moviebrowser.db;


public class DatabaseConstants {

    public static final String CREATE_STATEMENT =
            "CREATE TABLE " + MovieContract.TABLE_NAME + " (" +
                    MovieContract._ID + " INTEGER PRIMARY KEY, " +
                    MovieContract.COLUMN_NAME_ID + " TEXT, " +
                    MovieContract.COLUMN_NAME_TITLE + " TEXT, " +
                    MovieContract.COLUMN_NAME_YEAR + " TEXT, " +
                    MovieContract.COLUMN_NAME_POSTER + " TEXT, " +
                    MovieContract.COLUMN_NAME_STARRED + " INTEGER)";

}
