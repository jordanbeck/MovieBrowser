package com.twentyfivesquares.moviebrowser.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public interface OnMovieSelectedListener {
        void onMovieSelected(Movie movie);
    }

    private List<Movie> movies;
    private OnMovieSelectedListener listener;

    public MovieAdapter() {}

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void update(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setOnMovieSelectedListener(OnMovieSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.vName.setText(movie.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onMovieSelected(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView vName;

        public ViewHolder(View itemView) {
            super(itemView);
            vName = (TextView) itemView.findViewById(R.id.movie_name);
        }
    }

}
