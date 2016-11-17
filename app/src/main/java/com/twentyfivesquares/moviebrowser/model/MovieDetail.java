package com.twentyfivesquares.moviebrowser.model;

import com.google.gson.annotations.SerializedName;

import retrofit.Callback;

/**
 * This object is for when we fetch all the details for a movie. This is done
 *  using {@link com.twentyfivesquares.moviebrowser.api.MovieApi#fetchMovie(String, Callback)}.
 */
public class MovieDetail {

    @SerializedName("imdbID")
    public String id;

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Rated")
    public String rated;

    @SerializedName("Genre")
    public String genre;

    @SerializedName("Runtime")
    public String runtime;

    @SerializedName("Director")
    public String director;

    @SerializedName("Plot")
    public String plot;

    @SerializedName("Poster")
    public String poster;

    public boolean starred;
}
