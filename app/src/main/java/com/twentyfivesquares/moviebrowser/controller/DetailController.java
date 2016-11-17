package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.api.MovieApi;
import com.twentyfivesquares.moviebrowser.model.Movie;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailController extends TinyController {

    private ImageView vPoster;
    private TextView vTitle;
    private TextView vGenre;
    private TextView vMetadata;
    private TextView vDirector;
    private TextView vSummary;

    public DetailController(Context context, String movieId) {
        super(context);

        vPoster = (ImageView) findViewById(R.id.detail_poster);
        vTitle = (TextView) findViewById(R.id.detail_title);
        vGenre = (TextView) findViewById(R.id.detail_genre);
        vMetadata = (TextView) findViewById(R.id.detail_metadata);
        vDirector = (TextView) findViewById(R.id.detail_director);
        vSummary = (TextView) findViewById(R.id.detail_summary);

        MovieApi api = new MovieApi();
        api.fetchMovie(movieId, new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {
                populate(movie);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_detail;
    }

    private void populate(Movie movie) {
        vTitle.setText(movie.title);
        vGenre.setText(movie.genre);
        vDirector.setText(movie.director);
        vSummary.setText(TextUtils.isEmpty(movie.plot) ?
                getContext().getString(R.string.msg_no_summary) : movie.plot);
        vMetadata.setText(TextUtils.isEmpty(movie.rated) ?
                getContext().getString(R.string.label_movie_metadata_two, movie.year, movie.runtime) :
                getContext().getString(R.string.label_movie_metadata_three, movie.rated, movie.year, movie.runtime));

        Picasso.with(getContext()).load(movie.poster).into(vPoster);
    }
}
