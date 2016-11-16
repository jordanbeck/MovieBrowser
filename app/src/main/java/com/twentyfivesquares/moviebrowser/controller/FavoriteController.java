package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;
import android.view.ViewGroup;

import com.twentyfivesquares.moviebrowser.R;

public class FavoriteController extends TinyController {

    public FavoriteController(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_favorite;
    }
}
