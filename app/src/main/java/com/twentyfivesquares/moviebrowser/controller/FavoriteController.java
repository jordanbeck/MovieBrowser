package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;

import com.twentyfivesquares.moviebrowser.R;

public class FavoriteController extends TinyController {

    public FavoriteController(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_favorite;
    }
}
