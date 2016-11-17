package com.twentyfivesquares.moviebrowser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.twentyfivesquares.moviebrowser.controller.DetailController;

public class DetailActivity extends AppCompatActivity {

    public final static String EXTRA_MOVIE_ID = DetailActivity.class.getName() + ".MOVIE_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build the controller and set the view
        final String id = getIntent().getStringExtra(EXTRA_MOVIE_ID);
        DetailController controller = new DetailController(this, id);
        setContentView(controller.getView());

        // Initialize the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_detail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
