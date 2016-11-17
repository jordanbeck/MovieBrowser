package com.twentyfivesquares.moviebrowser.manager;


import com.twentyfivesquares.moviebrowser.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the manager object for accessing all persisted data for a specific model. I use these
 *  for all models and this allows me to switch out the data source and never impact the rest of
 *  production code.
 */
public class MovieManager {

    private static Map<String, Movie> movies;

    private static Map<String, Movie> getMovies() {
        if (movies == null) {
            movies = new HashMap<>();
            movies.put("tt0076759", new Movie("tt0076759",
                    "Star Wars: Episode IV - A New Hope", "1977",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BZGEzZTExMDEtNjg4OC00NjQxLTk5NTUtNjRkNjA3MmYwZjg1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                    true));
            movies.put("tt0080684", new Movie("tt0080684",
                    "Star Wars: Episode V - The Empire Strikes Back", "1980",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BYmViY2M2MTYtY2MzOS00YjQ1LWIzYmEtOTBiNjhlMGM0NjZjXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SX300.jpg",
                    true));
            movies.put("tt2488496", new Movie("tt2488496",
                    "Star Wars: The Force Awakens", "2015",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BOTAzODEzNDAzMl5BMl5BanBnXkFtZTgwMDU1MTgzNzE@._V1_SX300.jpg",
                    true));
        }

        return movies;
    }

    public static Movie fetchMovie(String id) {
        return getMovies().get(id);
    }

    public static List<Movie> fetchStarred() {
        return new ArrayList<>(getMovies().values());
    }

    public static boolean isStarred(String id) {
        return getMovies().containsKey(id);
    }
}
