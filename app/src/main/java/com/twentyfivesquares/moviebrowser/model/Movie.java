package com.twentyfivesquares.moviebrowser.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("imdbID")
    public String id;

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Rated")
    public String rated;

    @SerializedName("Released")
    public String released;

    @SerializedName("Director")
    public String director;

    @SerializedName("Plot")
    public String plot;

    @SerializedName("Poster")
    public String poster;

    public Movie(String title) {
        this.title = title;
    }
}
