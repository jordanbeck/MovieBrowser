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
import com.twentyfivesquares.moviebrowser.view.StarRibbonView;
import com.twentyfivesquares.moviebrowser.manager.MovieManager;
import com.twentyfivesquares.moviebrowser.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public interface OnMovieSelectedListener {
        void onMovieSelected(Movie movie);
    }

    private List<Movie> movies;
    private OnMovieSelectedListener listener;
    private MovieManager manager;

    public MovieAdapter(Context context) {
        this(context, null);
    }

    public MovieAdapter(Context context, List<Movie> movies) {
        this.manager = new MovieManager(context);
        this.movies = movies;
    }

    /**
     * Function to update the movies in the list
     * @param movies
     */
    public void update(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    /**
     * Set the listener for when a movie is selected from the list
     * @param listener
     */
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
        movie.starred = manager.isStarred(movie.id);

        // Set basic movie information
        holder.vName.setText(movie.title);
        holder.vMetadata.setText(movie.year);
        holder.vStar.setVisibility(movie.starred ? View.VISIBLE : View.GONE);

        // Load the photo
        Picasso.with(context).load(movie.poster).into(holder.vPoster);

        // Set the listener for when an item is selected
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


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView vName;
        TextView vMetadata;
        StarRibbonView vStar;
        ImageView vPoster;

        ViewHolder(View itemView) {
            super(itemView);
            // Save all the view for later use
            vName = (TextView) itemView.findViewById(R.id.movie_name);
            vMetadata = (TextView) itemView.findViewById(R.id.movie_metadata);
            vStar = (StarRibbonView) itemView.findViewById(R.id.movie_star);
            vPoster = (ImageView) itemView.findViewById(R.id.movie_poster);
        }
    }

}
