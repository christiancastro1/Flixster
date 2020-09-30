package com.example.flixster.models;

import android.content.pm.ModuleInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    int id;
    String posterPath;
    String title;
    String overview;
    double rating;

    // empty constructor for Pacel
    public Movie(){};

    public Movie(JSONObject object) throws JSONException {
        this.posterPath = object.getString("poster_path");
        this.title = object.getString("title");
        this.overview = object.getString("overview");
        this.rating = object.getDouble("vote_average");
        this.id = object.getInt("id");
    }

    public static List<Movie> fromJsonArray(JSONArray moviesArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for(int i=0; i < moviesArray.length(); i++) {
           movies.add(new Movie(moviesArray.getJSONObject(i)));
        }
        return movies;
    }
    public String getPosterPath() {

        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }
}


