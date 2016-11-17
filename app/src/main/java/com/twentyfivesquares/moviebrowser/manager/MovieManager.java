package com.twentyfivesquares.moviebrowser.manager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.twentyfivesquares.moviebrowser.db.MovieContract;
import com.twentyfivesquares.moviebrowser.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the manager object for accessing all persisted data for a specific model. I use these
 *  for all models and this allows me to switch out the data source and never impact the rest of
 *  production code.
 */
public class MovieManager extends Manager {

    public MovieManager(Context context) {
        super(context);
    }

    public Movie fetchMovie(String id) {
        String selection = MovieContract.COLUMN_NAME_ID + "=?";
        String[] selections = new String[]{id};
        Cursor cursor = getReadableDatabase().query(
                MovieContract.TABLE_NAME,
                MovieContract.COLUMNS,
                selection,
                selections,
                null, null, null);

        return getMovie(cursor);
    }

    public List<Movie> fetchStarred() {
        Cursor cursor = getReadableDatabase().query(
                MovieContract.TABLE_NAME,
                MovieContract.COLUMNS,
                null, null, null, null, null);

        return getMovies(cursor);
    }

    public boolean isStarred(String id) {
        String selection = MovieContract.COLUMN_NAME_ID + "=?";
        String[] selectionArgs = new String[]{id};
        Cursor cursor = getReadableDatabase().query(
                MovieContract.TABLE_NAME,
                MovieContract.COLUMNS,
                selection,
                selectionArgs,
                null, null, null);

        return cursor != null && cursor.getCount() > 0;
    }

    public long saveMovie(Movie movie) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MovieContract.COLUMN_NAME_ID, movie.id);
        values.put(MovieContract.COLUMN_NAME_TITLE, movie.title);
        values.put(MovieContract.COLUMN_NAME_YEAR, movie.year);
        values.put(MovieContract.COLUMN_NAME_POSTER, movie.poster);
        values.put(MovieContract.COLUMN_NAME_STARRED, movie.starred ? 1 : 0);

        // Insert the movie data and return the new row id
        return getWriteableDatabase().insert(MovieContract.TABLE_NAME, null, values);
    }

    public void deleteMovie(String id) {
        String selection = MovieContract.COLUMN_NAME_ID + "=?";
        String[] selectionArgs = new String[]{id};
        getWriteableDatabase().delete(MovieContract.TABLE_NAME, selection, selectionArgs);
    }

    private Movie getMovie(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }

        cursor.moveToFirst();

        Movie movie = new Movie();
        movie.id = cursor.getString(cursor.getColumnIndex(MovieContract.COLUMN_NAME_ID));
        movie.title = cursor.getString(cursor.getColumnIndex(MovieContract.COLUMN_NAME_TITLE));
        movie.year = cursor.getString(cursor.getColumnIndex(MovieContract.COLUMN_NAME_YEAR));
        movie.poster = cursor.getString(cursor.getColumnIndex(MovieContract.COLUMN_NAME_POSTER));
        movie.starred = cursor.getInt(cursor.getColumnIndex(MovieContract.COLUMN_NAME_STARRED)) == 1;

        cursor.close();

        return movie;
    }

    private List<Movie> getMovies(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return new ArrayList<>();
        }

        List<Movie> movies = new ArrayList<>();

        cursor.moveToFirst();

        for (int i = 0, size = cursor.getCount(); i < size; i++) {
            cursor.moveToPosition(i);

            Movie movie = new Movie();
            movie.id = cursor.getString(cursor.getColumnIndex(MovieContract.COLUMN_NAME_ID));
            movie.title = cursor.getString(cursor.getColumnIndex(MovieContract.COLUMN_NAME_TITLE));
            movie.year = cursor.getString(cursor.getColumnIndex(MovieContract.COLUMN_NAME_YEAR));
            movie.poster = cursor.getString(cursor.getColumnIndex(MovieContract.COLUMN_NAME_POSTER));
            final int starred = cursor.getInt(cursor.getColumnIndex(MovieContract.COLUMN_NAME_STARRED));
            movie.starred = starred == 1;

            movies.add(movie);
        }

        cursor.close();

        return movies;
    }
}
