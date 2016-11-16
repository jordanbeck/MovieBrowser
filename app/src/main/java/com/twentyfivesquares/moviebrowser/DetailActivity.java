package com.twentyfivesquares.moviebrowser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.twentyfivesquares.moviebrowser.controller.DetailController;

public class DetailActivity extends AppCompatActivity {

    public final static String EXTRA_MOVIE_ID = DetailActivity.class.getName() + ".MOVIE_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String id = getIntent().getStringExtra(EXTRA_MOVIE_ID);
        DetailController controller = new DetailController(this, id);
        setContentView(controller.getView());
    }
}
