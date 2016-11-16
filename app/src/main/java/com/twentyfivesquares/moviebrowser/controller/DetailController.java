package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.model.Movie;

public class DetailController extends TinyController {

    private ImageView vPoster;
    private TextView vTitle;
    private TextView vSummary;

    public DetailController(Context context, Movie movie) {
        super(context);

        vPoster = (ImageView) findViewById(R.id.detail_poster);
        Picasso.with(context).load(movie.poster).into(vPoster);

        vTitle = (TextView) findViewById(R.id.detail_title);
        vTitle.setText(movie.title);

        vSummary = (TextView) findViewById(R.id.detail_summary);
        vSummary.setText(TextUtils.isEmpty(movie.plot) ? context.getString(R.string.msg_no_summary) : movie.plot);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_detail;
    }
}
