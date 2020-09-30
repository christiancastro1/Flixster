package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.List;

import okhttp3.Headers;

public class DetailsActivity extends YouTubeBaseActivity {

    String YOUTUBE_KEY = "cant give you this";
    String VIDEO_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    String TAG = "DetailsActivity";
    TextView titleView;
    TextView overview;
    RatingBar ratingBar;
    YouTubePlayerView player;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleView = findViewById(R.id.detailsTitle);
        overview = findViewById(R.id.details_overview);
        ratingBar = findViewById(R.id.ratingBar);
        player = findViewById(R.id.player);


        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        assert movie != null;
        titleView.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        ratingBar.setRating((float)movie.getRating());
        
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEO_URL, movie.getId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "OnSuccess able to get the movie");
                JSONObject jsonobject = json.jsonObject;
                try {
                    JSONArray results = jsonobject.getJSONArray("results");
                    if (results.length() == 0){return;}
                    else
                        { 
                            final String videoId=results.getJSONObject(0).getString("key");
                            initializeYoutubeKey(videoId);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "OnFailure not able to get the movie");
            }
        });
    }
    private void initializeYoutubeKey(final String videoId) {
        player.initialize(YOUTUBE_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure:");

            }
        });
    }
}