package com.twentyfivesquares.moviebrowser.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twentyfivesquares.moviebrowser.R;
import com.twentyfivesquares.moviebrowser.controller.view.StarRibbonView;
import com.twentyfivesquares.moviebrowser.manager.MovieManager;
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
        final Context context = holder.itemView.getContext();
        final Movie movie = movies.get(position);
        // This object came from the web, so we need to update the starred value
        movie.starred = MovieManager.isStarred(context, movie.id);

        holder.vName.setText(movie.title);
        holder.vMetadata.setText(movie.year);
        holder.vStar.setVisibility(movie.starred ? View.VISIBLE : View.GONE);

        Picasso.with(context).load(movie.poster).into(holder.vPoster);

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
        public TextView vMetadata;
        public StarRibbonView vStar;
        public ImageView vPoster;

        public ViewHolder(View itemView) {
            super(itemView);

            vName = (TextView) itemView.findViewById(R.id.movie_name);
            vMetadata = (TextView) itemView.findViewById(R.id.movie_metadata);
            vStar = (StarRibbonView) itemView.findViewById(R.id.movie_star);
            vPoster = (ImageView) itemView.findViewById(R.id.movie_poster);
        }
    }

}
