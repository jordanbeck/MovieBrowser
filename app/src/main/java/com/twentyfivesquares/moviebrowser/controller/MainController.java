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

    private SearchController searchController;
    private FavoriteController favoriteController;

    public MainController(Context context, MovieAdapter.OnMovieSelectedListener movieSelectedListener) {
        super(context);

        this.searchController = new SearchController(getContext(), movieSelectedListener);
        this.favoriteController = new FavoriteController(getContext(), movieSelectedListener);

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

    @Override
    public void onResume() {
        super.onResume();
        searchController.onResume();
        favoriteController.onResume();
    }

    public void updateConnectivity() {
        searchController.updateConnectivity();
    }

    private class MainAdapter extends ViewPagerAdapter {

        @Override
        protected View getView(int position, ViewGroup container) {
            if (position == 0) {
                return searchController.getView();
            } else if (position == 1) {
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
