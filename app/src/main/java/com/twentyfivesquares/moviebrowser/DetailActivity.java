package com.twentyfivesquares.moviebrowser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.twentyfivesquares.moviebrowser.controller.DetailController;
import com.twentyfivesquares.moviebrowser.model.Movie;

public class DetailActivity extends AppCompatActivity {

    public final static String EXTRA_MOVIE = DetailActivity.class.getName() + ".MOVIE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Movie movie = (Movie) getIntent().getExtras().get(EXTRA_MOVIE);
        DetailController controller = new DetailController(this, movie);
        setContentView(controller.getView());
    }
}
