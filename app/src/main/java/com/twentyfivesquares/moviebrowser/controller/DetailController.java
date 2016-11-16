package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;
import android.widget.TextView;

import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.model.Movie;

public class DetailController extends TinyController {

    private TextView vTitle;

    public DetailController(Context context, Movie movie) {
        super(context);

        vTitle = (TextView) findViewById(R.id.detail_title);
        vTitle.setText(movie.title);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_detail;
    }
}
