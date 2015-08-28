package com.example.luisa.popularmovies.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.example.luisa.popularmovies.R;
import com.example.luisa.popularmovies.core.DataAccessObject;
import com.example.luisa.popularmovies.data.MoviesContract;
import com.example.luisa.popularmovies.rest.MovieRestService;

/**
 * Created by LuisA on 8/28/2015.
 */
public class MovieSyncAdapter extends AbstractThreadedSyncAdapter {

    public MovieSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        getContext().getContentResolver().bulkInsert(MoviesContract.MovieEntry.CONTENT_URI, DataAccessObject.toContentValues(MovieRestService.getMovies().getResults()));
    }

    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(new Account(
                        context.getString(R.string.app_name), context.getString(R.string.sync_account_type)),
                context.getString(R.string.content_authority), bundle);
    }
}
