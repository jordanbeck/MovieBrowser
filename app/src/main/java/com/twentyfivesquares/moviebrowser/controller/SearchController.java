package com.twentyfivesquares.moviebrowser.controller;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.StringRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.adapter.MovieAdapter;
import com.twentyfivesquares.moviebrowser.api.MovieApi;
import com.twentyfivesquares.moviebrowser.model.Movie;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Controller for the search list
 */
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

        // This is a hack to keep the keyboard hidden when the app launches (doesn't seem to
        // work in the emulator...)
        getView().post(new Runnable() {
            @Override
            public void run() {
                hideKeyboard(getContext());
            }
        });

        // Initialize the search box with a text watcher for real-time searching
        api = new MovieApi();
        vSearch = (EditText) findViewById(R.id.search_search);
        vSearch.setEnabled(isConnected());
        vSearch.addTextChangedListener(textWatcher);

        // Build the list
        adapter = new MovieAdapter(context);
        adapter.setOnMovieSelectedListener(movieSelectedListener);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        vList = (RecyclerView) findViewById(R.id.search_list);
        vList.setLayoutManager(layoutManager);
        vList.setAdapter(adapter);

        // Initialize the empty view
        vEmpty = (TextView) findViewById(R.id.search_empty);
        showEmpty();

        // Preload the search with the results from 'Star Wars' (just to keep the screen from being
        // empty on launch).
        search("Star Wars");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_search;
    }

    public void updateConnectivity() {
        final boolean connected = isConnected();
        vSearch.setEnabled(connected);
        if (!connected) {
            Toast.makeText(getContext(), R.string.msg_no_connectivity, Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void showEmpty() {
        showEmpty(R.string.msg_no_movies);
    }

    private void showEmpty(@StringRes int message) {
        showEmpty(getContext().getString(message));
    }

    private void showEmpty(String message) {
        if (vEmpty.getVisibility() != View.VISIBLE) {
            vList.setVisibility(View.GONE);
            vEmpty.setVisibility(View.VISIBLE);
        }

        vEmpty.setText(message);
    }

    private void showList() {
        if (vList.getVisibility() != View.VISIBLE) {
            vEmpty.setVisibility(View.GONE);
            vList.setVisibility(View.VISIBLE);
        }
    }

    private void search(final String searchTerm) {
        // Show an error message is there is no connection
        if (!isConnected()) {
            Toast.makeText(getContext(), R.string.msg_no_connectivity, Toast.LENGTH_SHORT).show();
            return;
        }

        if (searchTerm.length() < 3) {
            // Don't execute searches under three characters. Clear out the list.
            adapter.update(null);
            showEmpty();
            return;
        }

        // TODO: Add the ability to cancel this transaction if it doesn't return before the next search query
        api.search(searchTerm, new Callback<List<Movie>>() {
            @Override
            public void success(List<Movie> movies, Response response) {
                // Update the adapter and show the list/empty view
                adapter.update(movies);
                if (movies == null || movies.size() == 0) {
                    showEmpty(getContext().getString(R.string.msg_no_movies_search, searchTerm));
                } else {
                    showList();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                // Show an error message
                showEmpty(R.string.msg_search_error);
            }
        });
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void hideKeyboard(Context context) {
        View view = ((Activity) context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
