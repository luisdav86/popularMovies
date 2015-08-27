package com.example.luisa.popularmovies.sync;

import com.example.luisa.popularmovies.entity.Result;

import java.util.List;

/**
 * Created by LuisA on 8/27/2015.
 */
public class MovieResponse {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


}
