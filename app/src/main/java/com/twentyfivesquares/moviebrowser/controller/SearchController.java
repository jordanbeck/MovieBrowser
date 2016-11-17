package com.twentyfivesquares.moviebrowser.controller;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

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
    private TextView vEmpty;
    private MovieAdapter adapter;

    public SearchController(Context context, MovieAdapter.OnMovieSelectedListener movieSelectedListener) {
        super(context);

        // This is a hack to keep the keyboard hidden when the app launches
        getView().post(new Runnable() {
            @Override
            public void run() {
                hideKeyboard(getContext());
            }
        });

        adapter = new MovieAdapter();
        adapter.setOnMovieSelectedListener(movieSelectedListener);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        vList = (RecyclerView) findViewById(R.id.search_list);
        vList.setLayoutManager(layoutManager);
        vList.setAdapter(adapter);

        vEmpty = (TextView) findViewById(R.id.search_empty);

        MovieApi api = new MovieApi();
        api.search("Star Wars", new Callback<List<Movie>>() {
            @Override
            public void success(List<Movie> movies, Response response) {
                showList();
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

    private void hideKeyboard(Context context) {
        View view = ((Activity) context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
