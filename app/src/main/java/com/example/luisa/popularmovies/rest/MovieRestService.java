package com.example.luisa.popularmovies.rest;

import android.text.TextUtils;

import com.example.luisa.popularmovies.Utility;
import com.example.luisa.popularmovies.core.LogIt;

import retrofit.http.GET;
import retrofit.RestAdapter;
import retrofit.http.Query;


/**
 * Created by LuisA on 8/27/2015.
 */
public class MovieRestService {

    private static final String PUPULARITY_ORDER = "popularity.desc";
    private static final String AVERAGE_ORDER = "vote_average.desc";

    private static final String API_URL = "http://api.themoviedb.org";
    private static final String API_FIELDS = "/3/discover/movie?";

    interface IMovies {
        @GET(API_FIELDS)
        MovieRequest getMovies(@Query("sort_by") String sortBy, @Query("api_key") String apyKey);
    }

    public static MovieRequest getMovies(String sortCriteria, String apiKey) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();
        IMovies api = restAdapter.create(IMovies.class);
        if (!sortCriteria.equals(AVERAGE_ORDER)) {
            sortCriteria = PUPULARITY_ORDER;
        }
        return api.getMovies(sortCriteria, apiKey);
    }
}
