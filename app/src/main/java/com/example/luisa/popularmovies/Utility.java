package com.example.luisa.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Arcia on 8/30/2015.
 */
public class Utility {

    public static String getPreferredLocation(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_movie_key),
                context.getString(R.string.pref_movie_popular));
    }

    public static boolean getPreferredFirstSync(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(context.getString(R.string.pref_first_sync), false);
    }

    public static void putPreferredFirstSync(Context context, boolean firstSync) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(context.getString(R.string.pref_first_sync), firstSync);
        editor.commit();
    }

}
