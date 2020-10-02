package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailsActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;
import org.parceler.Parcels;
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
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDescription = itemView.findViewById(R.id.ivDescription);
            ivTitle = itemView.findViewById(R.id.ivTitle);
            ivPoster = itemView.findViewById(R.id.ivImageView);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            ivDescription.setText(movie.getOverview());
            ivTitle.setText(movie.getTitle());
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
            final String title = movie.getTitle();
            final String overview = movie.getOverview();

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(intent);
                }
            });
        }
    }
}


/*


Intent intent = new Intent(this, DetailsActivity.class);
// Pass data object in the bundle and populate details activity.
intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
ActivityOptionsCompat options = ActivityOptionsCompat.
    makeSceneTransitionAnimation(MainActivity.this, (View)ivProfile, "profile");
startActivity(intent, options.toBundle());
 */
