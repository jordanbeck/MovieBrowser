package com.twentyfivesquares.moviebrowser.controller;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.adapter.MovieAdapter;
import com.twentyfivesquares.moviebrowser.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class SearchController extends TinyController {

    private RecyclerView vList;
    private MovieAdapter adapter;

    public SearchController(Context context, MovieAdapter.OnMovieSelectedListener movieSelectedListener) {
        super(context);

        List<Movie> tempMovies = new ArrayList<>();
        tempMovies.add(new Movie("Star Wars: A New Hope"));
        tempMovies.add(new Movie("Star Wars: Empire Strikes Back"));
        tempMovies.add(new Movie("Star Wars: Return of the Jedi"));
        tempMovies.add(new Movie("Star Wars: The Force Awakens"));

        adapter = new MovieAdapter(tempMovies);
        adapter.setOnMovieSelectedListener(movieSelectedListener);

        vList = (RecyclerView) findViewById(R.id.search_list);
        vList.setLayoutManager(new LinearLayoutManager(context));
        vList.setAdapter(adapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_search;
    }
}
