package com.twentyfivesquares.moviebrowser.db;


import android.provider.BaseColumns;

/**
 * Add database constants for the movie table
 */
public class MovieContract implements BaseColumns {

    private MovieContract() {
        // Prevent anyone from building an instance
    }

    public static final String TABLE_NAME = "movie";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_YEAR = "year";
    public static final String COLUMN_NAME_POSTER = "poster";
    public static final String COLUMN_NAME_STARRED = "starred";
    // Convenient array for running queries that select all
    public static final String[] COLUMNS = {
            TABLE_NAME + "." + COLUMN_NAME_ID,
            TABLE_NAME + "." + COLUMN_NAME_TITLE,
            TABLE_NAME + "." + COLUMN_NAME_YEAR,
            TABLE_NAME + "." + COLUMN_NAME_POSTER,
            TABLE_NAME + "." + COLUMN_NAME_STARRED
    };
}
