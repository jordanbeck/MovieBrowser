package com.twentyfivesquares.moviebrowser.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import retrofit.Callback;

/**
 * This object is used for the search results. It does not contain all of the information about
 *  movie. This is used in {@link com.twentyfivesquares.moviebrowser.api.MovieApi#search(String, Callback)}.
 */
public class Movie implements Parcelable {

    @SerializedName("imdbID")
    public String id;

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Poster")
    public String poster;

    public boolean starred;

    public Movie() {}

    public Movie(String id, String title, String year, String poster, boolean starred) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.poster = poster;
        this.starred = starred;
    }

    private Movie(Parcel parcel) {
        id = parcel.readString();
        title = parcel.readString();
        year = parcel.readString();
        poster = parcel.readString();
        starred = Boolean.parseBoolean(parcel.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(year);
        parcel.writeString(poster);
        parcel.writeString(Boolean.toString(starred));
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };
}
