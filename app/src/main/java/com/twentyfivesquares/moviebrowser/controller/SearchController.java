package com.twentyfivesquares.moviebrowser.controller;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.adapter.MovieAdapter;
import com.twentyfivesquares.moviebrowser.api.MovieApi;
import com.twentyfivesquares.moviebrowser.model.Movie;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchController extends TinyController {

    private RecyclerView vList;
    private MovieAdapter adapter;

    public SearchController(Context context, MovieAdapter.OnMovieSelectedListener movieSelectedListener) {
        super(context);

        adapter = new MovieAdapter();
        adapter.setOnMovieSelectedListener(movieSelectedListener);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        vList = (RecyclerView) findViewById(R.id.search_list);
        vList.setLayoutManager(layoutManager);
        vList.setAdapter(adapter);

        MovieApi api = new MovieApi();
        api.search("Star Wars", new Callback<List<Movie>>() {
            @Override
            public void success(List<Movie> movies, Response response) {
                adapter.update(movies);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("UI", error.getMessage());
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_search;
    }
}
