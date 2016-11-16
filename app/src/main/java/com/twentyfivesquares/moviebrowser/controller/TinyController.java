package com.twentyfivesquares.moviebrowser.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This is a class that I using instead of fragments. I have a lot of plans for it, but currently
 * they are all unfinished. For now, this is the scaled down version.
 */
public abstract class TinyController {

    private final int NO_VIEW = -1;

    private final Context context;
    private View view;

    /**
     * Get the layout resource that this controller will be using. This will be used when
     * the view is being built inside the controller instead of being passed in.
     *
     * @return The resource id for the layout being used.
     */
    protected abstract @LayoutRes int getLayoutRes();

    public TinyController(Context context) {
        this(context, null, null);
    }

    public TinyController(Context context, ViewGroup parent) {
        this(context, parent, null);
    }

    public TinyController(Context context, ViewGroup parent, Bundle savedInstanceState) {
        this.context = context;
        if (getLayoutRes() != NO_VIEW) {
            View view = LayoutInflater.from(context).inflate(getLayoutRes(), parent, parent != null);
            setView(view);
        }
        if (savedInstanceState != null && savedInstanceState.size() > 0) {
            resumeSavedState(savedInstanceState);
        }
    }

    /**
     * Set a main view that will be used for this controller.
     *
     * @param view The main View for this controller.
     */
    protected void setView(View view) {
        this.view = view;
    }

    /**
     * Called when a constructor is called with a {@link Bundle}. Should be used in conjunction
     * with {@link #onSavedInstanceState(Bundle)}.
     *
     * @param savedInstanceState    Bundle which contains saved state information.
     */
    protected void resumeSavedState(Bundle savedInstanceState) {
        // TODO: do anything here for all controllers?
    }

    /**
     * Save any state specific information. You can then pass it into the constructor which will
     * forward it on to {@link #resumeSavedState}. This is meant to mimic the
     * {@link Activity#onSaveInstanceState(Bundle)} and be called at the same time.
     *
     * @param outState  Bundle in which to place your saved state.
     */
    public void onSavedInstanceState(Bundle outState) {
        // TODO: do anything here for all controllers?
    }

    /**
     * Properly resume this controller and associated view. This is meant to mimic the
     * {@link Activity#onResume()} and be called at the same time.
     */
    public void onResume() {
        // TODO: implement everything to properly resume the views
    }

    /**
     * Properly pause this controller and associated view. This is meant to mimic the
     * {@link Activity#onPause()} and be called at the same time.
     */
    public void onPause() {
        // TODO: implement everything to properly pause the views
    }

    /**
     * Properly destroy this controller and associated view. This is meant to mimic the
     * {@link Activity#onDestroy()} and be called at the same time.
     */
    @CallSuper
    public void onDestroy() {
        // TODO: implement everything to properly destroy the views
    }

    /**
     * Get the context used to create the views.
     *
     * @return Context object
     */
    protected Context getContext() {
        return this.context;
    }

    /**
     * Get the view associated with this controller
     *
     * @return The view for this controller
     */
    public View getView() {
        return this.view;
    }

    /**
     * Look for a child view contained within this controller. If none is found, return null.
     *
     * @param id The id for the view you are looking for.
     *
     * @return the view with the given id or null if no View is found.
     */
    public View findViewById(int id) {
        if (view == null) {
            throw new IllegalStateException("View has not been set for this controller.");
        }

        return view.findViewById(id);
    }

    public void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
