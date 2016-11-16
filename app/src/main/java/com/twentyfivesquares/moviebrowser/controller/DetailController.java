package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;

import com.twentyfivesquares.moviebrowser.R;

public class DetailController extends TinyController {

    public DetailController(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_detail;
    }
}
