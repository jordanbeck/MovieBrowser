package com.twentyfivesquares.moviebrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twentyfivesquares.moviebrowser.adapter.MovieAdapter;
import com.twentyfivesquares.moviebrowser.controller.MainController;
import com.twentyfivesquares.moviebrowser.model.Movie;

public class MainActivity extends AppCompatActivity
                          implements MovieAdapter.OnMovieSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MainController controller = new MainController(this, this);
        setContentView(controller.getView());
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(DetailActivity.EXTRA_MOVIE_ID, movie.id);
        startActivity(i);
    }
}
