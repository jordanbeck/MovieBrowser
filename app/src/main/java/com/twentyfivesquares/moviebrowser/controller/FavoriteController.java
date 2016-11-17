package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.adapter.MovieAdapter;
import com.twentyfivesquares.moviebrowser.manager.MovieManager;
import com.twentyfivesquares.moviebrowser.model.Movie;

import java.util.List;

/**
 * Controller for the favorite list screen
 */
public class FavoriteController extends TinyController {

    private RecyclerView vList;
    private View vEmpty;

    public FavoriteController(Context context, MovieAdapter.OnMovieSelectedListener movieSelectedListener) {
        super(context);

        // Initialize the emoty view
        vEmpty = findViewById(R.id.favorite_empty);

        // Initialize the list and adapter
        MovieManager manager = new MovieManager(context);
        List<Movie> movies = manager.fetchStarred();
        MovieAdapter adapter = new MovieAdapter(context, movies);
        adapter.setOnMovieSelectedListener(movieSelectedListener);
        vList = (RecyclerView) findViewById(R.id.favorite_list);
        vList.setLayoutManager(new GridLayoutManager(context, 2));
        vList.setAdapter(adapter);

        // Show the empty screen if necessary
        if (movies == null || movies.size() == 0) {
            showEmpty();
        } else {
            showList();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_favorite;
    }

    private void showEmpty() {
        if (vEmpty.getVisibility() != View.VISIBLE) {
            vList.setVisibility(View.GONE);
            vEmpty.setVisibility(View.VISIBLE);
        }
    }

    private void showList() {
        if (vList.getVisibility() != View.VISIBLE) {
            vEmpty.setVisibility(View.GONE);
            vList.setVisibility(View.VISIBLE);
        }
    }
}
