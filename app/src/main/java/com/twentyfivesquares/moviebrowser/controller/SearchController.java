package com.twentyfivesquares.moviebrowser.controller;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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

    private EditText vSearch;
    private RecyclerView vList;
    private TextView vEmpty;

    private MovieApi api;
    private MovieAdapter adapter;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            search(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    public SearchController(Context context, MovieAdapter.OnMovieSelectedListener movieSelectedListener) {
        super(context);

        // This is a hack to keep the keyboard hidden when the app launches
        getView().post(new Runnable() {
            @Override
            public void run() {
                hideKeyboard(getContext());
            }
        });

        // Initialize the search box with a text watcher
        api = new MovieApi();
        vSearch = (EditText) findViewById(R.id.search_search);
        vSearch.addTextChangedListener(textWatcher);

        // Build the list
        adapter = new MovieAdapter();
        adapter.setOnMovieSelectedListener(movieSelectedListener);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        vList = (RecyclerView) findViewById(R.id.search_list);
        vList.setLayoutManager(layoutManager);
        vList.setAdapter(adapter);

        // Initialize the empty view
        vEmpty = (TextView) findViewById(R.id.search_empty);
        showEmpty();

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
        showEmpty(null);
    }

    private void showEmpty(String searchTerm) {
        if (vEmpty.getVisibility() != View.VISIBLE) {
            vList.setVisibility(View.GONE);
            vEmpty.setVisibility(View.VISIBLE);
        }

        vEmpty.setText(TextUtils.isEmpty(searchTerm) ?
                getContext().getString(R.string.msg_no_movies) :
                getContext().getString(R.string.msg_no_movies_search, searchTerm));
    }

    private void showList() {
        if (vList.getVisibility() != View.VISIBLE) {
            vEmpty.setVisibility(View.GONE);
            vList.setVisibility(View.VISIBLE);
        }
    }

    private void search(final String searchTerm) {
        if (searchTerm.length() < 3) {
            // Don't execute searches under three characters. Clear out the list.
            adapter.update(null);
            showEmpty();
            return;
        }

        api.search(searchTerm, new Callback<List<Movie>>() {
            @Override
            public void success(List<Movie> movies, Response response) {
                adapter.update(movies);

                if (movies == null || movies.size() == 0) {
                    showEmpty(searchTerm);
                } else {
                    showList();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
