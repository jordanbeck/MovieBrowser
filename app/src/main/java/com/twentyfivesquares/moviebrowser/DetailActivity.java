package com.twentyfivesquares.moviebrowser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.twentyfivesquares.moviebrowser.controller.DetailController;

public class DetailActivity extends AppCompatActivity {

    public final static String EXTRA_MOVIE_TITLE = DetailActivity.class.getName() + ".MOVIE_TITLE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DetailController controller = new DetailController(this);
        setContentView(controller.getView());
    }
}
