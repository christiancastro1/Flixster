package com.example.flixster.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

public class MovieApapter extends RecyclerView.Adapter<MovieApapter.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MovieApapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.activity_main,parent,false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ivDescription;
        TextView ivTitle;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDescription = itemView.findViewById(R.id.ivDescription);
            ivTitle = itemView.findViewById(R.id.ivTitle);
            ivPoster = itemView.findViewById(R.id.ivImageView);
        }

        public void bind(Movie movie) {
            ivDescription.setText(movie.getOverview());
            ivTitle.setText(movie.getTitle());
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
        }
    }
}
