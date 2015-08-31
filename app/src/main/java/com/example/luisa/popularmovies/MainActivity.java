package com.example.luisa.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.luisa.popularmovies.core.DataAccessObject;
import com.example.luisa.popularmovies.data.MoviesContract;
import com.example.luisa.popularmovies.rest.MovieRequest;
import com.example.luisa.popularmovies.rest.MovieRestService;
import com.example.luisa.popularmovies.sync.MovieSyncAdapter;

public class MainActivity extends AppCompatActivity implements MovieFragment.Callback {

    private static final String DETAIL_FRAGMENT_TAG = "DFTAG";

    private String mMovieOrder;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieOrder = Utility.getPreferredLocation(this);
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.movie_detail_container, new MovieDetailFragment(), DETAIL_FRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
        }

        MovieSyncAdapter.initializeSyncAdapter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String movieOrder = Utility.getPreferredLocation(this);
        // update the location in our second pane using the fragment manager
        if (movieOrder != null && !movieOrder.equals(mMovieOrder)) {
            MovieFragment ff = (MovieFragment) getSupportFragmentManager().findFragmentById(R.id.movie_fragment);
            if (null != ff) {
                ff.onOrderMovieChanged();
            }
            MovieDetailFragment df = (MovieDetailFragment) getSupportFragmentManager().findFragmentByTag(DETAIL_FRAGMENT_TAG);
            if (null != df) {
                df.onOrderMovieChanged("-1");
            }
            mMovieOrder = movieOrder;
        }
    }

    @Override
    public void onItemSelected(Uri contentUri) {
        if (mTwoPane) {

        } else {
            Intent intent = new Intent(this, MovieDetailActivity.class)
                    .setData(contentUri);
            startActivity(intent);
        }
    }
}
