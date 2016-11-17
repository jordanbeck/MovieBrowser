package com.twentyfivesquares.moviebrowser.api;


import retrofit.RestAdapter;

/**
 * Note: I always use a class like this for building a common adapter for all API endpoints. It's
 *  not entirely necessary for this project, but I just wanted to show it.
 */
class Api {
    private final String ENDPOINT = "http://www.omdbapi.com";
    private final RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.NONE;

    private RestAdapter adapter;

    RestAdapter getAdapter() {
        if (adapter == null) {
            adapter = new RestAdapter.Builder()
                    .setEndpoint(ENDPOINT)
                    .setLogLevel(LOG_LEVEL)
                    .build();
        }

        return adapter;
    }
}
