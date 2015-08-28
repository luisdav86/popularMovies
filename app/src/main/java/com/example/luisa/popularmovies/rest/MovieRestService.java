package com.example.luisa.popularmovies.rest;

import retrofit.http.GET;
import retrofit.RestAdapter;


/**
 * Created by LuisA on 8/27/2015.
 */
public class MovieRestService {

    private static final String API_URL = "http://api.themoviedb.org";
    private static final String API_FIELDS = "/3/discover/movie?sort_by=popularity.desc&api_key=1a6321ee87822a379dbf9f8f2c37107e";

    interface IMovies {
        @GET(API_FIELDS)
        Object getVenues(/*@Query("ll") String ll,*/
        );

        @GET(API_FIELDS)
            // MovieRequest getVenuesByQuery(@Query("query") String query, @Query("radius") int radius);
        MovieRequest getMovies();

    }

    public static MovieRequest getMovies() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();
        IMovies api = restAdapter.create(IMovies.class);
        return api.getMovies();
    }
}
