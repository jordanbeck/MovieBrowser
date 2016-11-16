package com.twentyfivesquares.moviebrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twentyfivesquares.moviebrowser.controller.MainController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MainController controller = new MainController(this);
        setContentView(controller.getView());
    }
}
