package com.twentyfivesquares.moviebrowser.api;

import com.google.gson.annotations.SerializedName;
import com.twentyfivesquares.moviebrowser.model.Movie;
import com.twentyfivesquares.moviebrowser.model.MovieDetail;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

public class MovieApi extends Api {

    private final static String PLOT_FULL = "full";

    private interface MovieService {
        @GET("/")
        void fetchMovie(@Query("i") String id,
                        @Query("plot") String plot,
                        Callback<MovieDetail> callback);

        @GET("/")
        void search(@Query("s") String search,
                    Callback<ResponseSearch> callback);
    }

    private MovieService movieService;

    public MovieApi() {
        movieService = getAdapter().create(MovieService.class);
    }

    public void fetchDetails(String id, final Callback<MovieDetail> callback) {
        movieService.fetchMovie(id, PLOT_FULL, callback);
    }

    public void search(String search, final Callback<List<Movie>> callback) {
        /**
         * This wrapped callback is because of the way the API returns the data. I do this to get
         *  rid of the "Search" object at the beginning of the JSON blob. See {@link ResponseSearch}.
         */
        movieService.search(search, new Callback<ResponseSearch>() {
            @Override
            public void success(ResponseSearch responseSearch, Response response) {
                callback.success(responseSearch.movies, response);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error);
            }
        });
    }

    /**
     * Had to use an object like this because the search API return an object first
     *  and not just a list of movies. This object should not be used anywhere except
     *  in MovieApi.
     */
    private class ResponseSearch {
        @SerializedName("Search")
        public List<Movie> movies;
    }
}
