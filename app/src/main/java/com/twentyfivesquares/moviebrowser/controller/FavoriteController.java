package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.adapter.MovieAdapter;
import com.twentyfivesquares.moviebrowser.manager.MovieManager;

public class FavoriteController extends TinyController {

    private RecyclerView vList;

    public FavoriteController(Context context) {
        super(context);

        MovieAdapter adapter = new MovieAdapter(MovieManager.fetchStarred());
        vList = (RecyclerView) findViewById(R.id.favorite_list);
        vList.setLayoutManager(new GridLayoutManager(context, 2));
        vList.setAdapter(adapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_favorite;
    }
}
