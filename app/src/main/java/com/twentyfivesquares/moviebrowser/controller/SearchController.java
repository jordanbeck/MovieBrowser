package com.twentyfivesquares.moviebrowser.controller;


import android.content.Context;
import android.view.ViewGroup;

import com.twentyfivesquares.moviebrowser.R;

public class SearchController extends TinyController {

    public SearchController(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_search;
    }
}
