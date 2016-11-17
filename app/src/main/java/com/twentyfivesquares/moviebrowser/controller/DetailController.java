package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.api.MovieApi;
import com.twentyfivesquares.moviebrowser.manager.MovieManager;
import com.twentyfivesquares.moviebrowser.model.Movie;
import com.twentyfivesquares.moviebrowser.model.MovieDetail;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Controller for the movie detail screen
 */
public class DetailController extends TinyController {

    private ImageView vPoster;
    private TextView vTitle;
    private TextView vGenre;
    private TextView vMetadata;
    private TextView vDirector;
    private TextView vSummary;
    private FloatingActionButton vStarButton;

    private Movie movie;
    private MovieDetail movieDetail;
    private MovieManager manager;

    public DetailController(Context context, Movie movie) {
        super(context);

        this.movie = movie;
        this.manager = new MovieManager(context);

        // Initialize all views
        vPoster = (ImageView) findViewById(R.id.detail_poster);
        vTitle = (TextView) findViewById(R.id.detail_title);
        vGenre = (TextView) findViewById(R.id.detail_genre);
        vMetadata = (TextView) findViewById(R.id.detail_metadata);
        vDirector = (TextView) findViewById(R.id.detail_director);
        vSummary = (TextView) findViewById(R.id.detail_summary);
        vStarButton = (FloatingActionButton) findViewById(R.id.detail_star_button);
        vStarButton.setImageResource(movie.starred ? R.drawable.ic_star : R.drawable.ic_star_empty_24dp);
        vStarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleStar();
            }
        });

        // Fetch the details for the movie. This will get the rating, plot, etc.
        MovieApi api = new MovieApi();
        api.fetchDetails(movie.id, new Callback<MovieDetail>() {
            @Override
            public void success(MovieDetail movieDetail, Response response) {
                populate(movieDetail);
            }

            @Override
            public void failure(RetrofitError error) {}
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_detail;
    }

    private void populate(MovieDetail movieDetail) {
        this.movieDetail = movieDetail;

        // Populate the view with the movie details
        vTitle.setText(movieDetail.title);
        vGenre.setText(movieDetail.genre);
        vDirector.setText(movieDetail.director);
        vSummary.setText(TextUtils.isEmpty(movieDetail.plot) ?
                getContext().getString(R.string.msg_no_summary) : movieDetail.plot);
        vMetadata.setText(TextUtils.isEmpty(movieDetail.rated) ?
                getContext().getString(R.string.label_movie_metadata_two, movieDetail.year, movieDetail.runtime) :
                getContext().getString(R.string.label_movie_metadata_three, movieDetail.rated, movieDetail.year, movieDetail.runtime));
        vStarButton.setImageResource(movie.starred ? R.drawable.ic_star : R.drawable.ic_star_empty_24dp);

        // Load the poster image
        Picasso.with(getContext()).load(movieDetail.poster).into(vPoster);
    }

    private void toggleStar() {
        movie.starred = !movie.starred;
        // Update the star in the FAB
        vStarButton.setImageResource(movie.starred ? R.drawable.ic_star : R.drawable.ic_star_empty_24dp);

        // Update the database
        if (movie.starred) {
            manager.saveMovie(movie);
        } else {
            manager.deleteMovie(movie.id);
        }
    }
}
