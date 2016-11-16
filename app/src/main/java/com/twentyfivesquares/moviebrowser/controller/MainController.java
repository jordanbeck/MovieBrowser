package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;

import com.twentyfivesquares.moviebrowser.R;

public class MainController extends TinyController {

    public MainController(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_main;
    }
}
