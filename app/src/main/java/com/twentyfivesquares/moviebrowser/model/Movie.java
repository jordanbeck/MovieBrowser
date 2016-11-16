package com.twentyfivesquares.moviebrowser.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    @SerializedName("imdbID")
    public String id;

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Rated")
    public String rated;

    @SerializedName("Director")
    public String director;

    @SerializedName("Plot")
    public String plot;

    @SerializedName("Poster")
    public String poster;

    public Movie() {}

    private Movie(Parcel parcel) {
        id = parcel.readString();
        title = parcel.readString();
        year = parcel.readString();
        rated = parcel.readString();
        director = parcel.readString();
        plot = parcel.readString();
        poster = parcel.readString();
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
        parcel.writeString(rated);
        parcel.writeString(director);
        parcel.writeString(plot);
        parcel.writeString(poster);
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
