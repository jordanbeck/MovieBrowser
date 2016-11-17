package com.twentyfivesquares.moviebrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.twentyfivesquares.moviebrowser.adapter.MovieAdapter;
import com.twentyfivesquares.moviebrowser.controller.MainController;
import com.twentyfivesquares.moviebrowser.model.Movie;

public class MainActivity extends AppCompatActivity
                          implements MovieAdapter.OnMovieSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build the controller and set the view
        final MainController controller = new MainController(this, this);
        setContentView(controller.getView());

        // Initialize the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(DetailActivity.EXTRA_MOVIE_ID, movie.id);
        startActivity(i);
    }
}
