package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.jokes.Joker;
import com.udacity.gradle.jokesbackend.myApi.MyApi;

import java.io.IOException;

import gradle.udacity.com.jokeractivity.JokerActivity;


public class MainActivity extends ActionBarActivity implements EndpointListener{



    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String EXTRA_JOKE = "com.udacity.gradle.builditbigger.extra_joke";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "Calling async");
        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Ass Hole"));
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

    public void tellJoke(View view){

//        Intent intent = new Intent(this, JokerActivity.class);
//        Joker joker = new Joker();
//        //Toast.makeText(this, joker.getJoke(), Toast.LENGTH_LONG).show();
//        intent.putExtra(EXTRA_JOKE,joker.getJoke());
//        startActivity(intent);

        new EndpointAsyncTask().execute(this);
    }


    @Override
    public void fetchJokeComplete(String joke) {
        Intent intent = new Intent(this, JokerActivity.class);

        intent.putExtra(EXTRA_JOKE,joke);
        startActivity(intent);
    }
}
