package com.example.luisa.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.luisa.popularmovies.sync.MovieRequest;
import com.example.luisa.popularmovies.sync.MovieRestService;

import java.net.URL;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Test().execute();

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class Test extends AsyncTask<Void, Void, MovieRequest> {

        @Override
        protected MovieRequest doInBackground(Void... params) {
            return MovieRestService.getMovies();
        }

        @Override
        protected void onPostExecute(MovieRequest object) {
            super.onPostExecute(object);
            if (object != null) {
                Toast.makeText(MainActivity.this, "exito", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(MainActivity.this, "mal", Toast.LENGTH_SHORT);
            }

        }
    }
}
