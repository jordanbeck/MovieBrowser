package com.twentyfivesquares.moviebrowser.controller;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.adapter.MovieAdapter;
import com.twentyfivesquares.moviebrowser.controller.view.ViewPagerAdapter;

public class MainController extends TinyController {

    private final MovieAdapter.OnMovieSelectedListener movieSelectedListener;

    public MainController(Context context, MovieAdapter.OnMovieSelectedListener movieSelectedListener) {
        super(context);

        this.movieSelectedListener = movieSelectedListener;

        TabLayout vTabs = (TabLayout) findViewById(R.id.main_tabs);
        ViewPager vPager = (ViewPager) findViewById(R.id.main_pager);
        MainAdapter adapter = new MainAdapter();

        // Initialize the tabs with pager information
        vTabs.setupWithViewPager(vPager);

        // Update pager with adapter
        vPager.setAdapter(adapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_main;
    }

    private class MainAdapter extends ViewPagerAdapter {

        private SearchController searchController;
        private FavoriteController favoriteController;

        @Override
        protected View getView(int position, ViewGroup container) {
            if (position == 0) {
                // Initialize the controller the first time.
                if (searchController == null) {
                    searchController = new SearchController(getContext(), movieSelectedListener);
                }
                return searchController.getView();
            } else if (position == 1) {
                // Initialize the controller the first time.
                if (favoriteController == null) {
                    favoriteController = new FavoriteController(getContext());
                }
                return favoriteController.getView();
            }

            throw new IllegalStateException("Position " + position + " not available");
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Page title is used for the tabs via TabLayout.setupWithViewPager() above
            if (position == 0) {
                return getContext().getString(R.string.label_search);
            } else if (position == 1) {
                return getContext().getString(R.string.label_favorites);
            }

            throw new IllegalStateException("Position " + position + " not available");
        }
    }
}
