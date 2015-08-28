package com.example.luisa.popularmovies.data;

import android.provider.BaseColumns;

/**
 * Created by LuisA on 8/28/2015.
 */
public class DBConstants {

    public static final String MOVIES_TABLE_NAME = "movies";

    public interface MovieColumns extends BaseColumns {
        String ORIGINAL_TITLE = "original_title";
        String POSTER_PATH = "poster_path";
        String OVERVIEW = "overview";
        String VOTE_AVERAGE = "vote_average";
        String RELEASE_DATE = "release_date";
    }
}
