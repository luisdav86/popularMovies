package com.example.luisa.popularmovies.rest;

import com.example.luisa.popularmovies.Utility;

import retrofit.http.GET;
import retrofit.RestAdapter;
import retrofit.http.Query;


/**
 * Created by LuisA on 8/27/2015.
 */
public class MovieRestService {

    private static final String DESC_ORDER = "desc";
    private static final String PUPULARITY_ORDER = "popularity.desc";
    private static final String AVERAGE_ORDER = "vote_average.desc";

    private static final String API_URL = "http://api.themoviedb.org";
    private static final String API_FIELDS = "/3/discover/movie?&api_key=1a6321ee87822a379dbf9f8f2c37107e";

    interface IMovies {
        @GET(API_FIELDS)
        MovieRequest getMovies(@Query("sort_by") String sortBy);
    }

    public static MovieRequest getMovies(String sortCriteria) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();
        IMovies api = restAdapter.create(IMovies.class);
        if (!sortCriteria.equals(AVERAGE_ORDER)) {
            sortCriteria = PUPULARITY_ORDER;
        }
        return api.getMovies(sortCriteria);
    }
}
