package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import gradle.udacity.com.jokeractivity.JokerActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointListener{

    private Button mJokeButton;
    private String mJoke;
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mJokeButton = (Button)root.findViewById(R.id.joke_button);
        mJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                tellJoke();
            }
        });

        mProgressBar = (ProgressBar)root.findViewById(R.id.joke_fetch_progress_spinner);
        mProgressBar.setVisibility(View.GONE);
        return root;
    }

    public void tellJoke(){
        //Fetch Jokes from GCM
        new EndpointAsyncTask().execute(this);
    }

    @Override
    public void fetchJokeComplete(String joke) {
        mJoke = joke;
        mProgressBar.setVisibility(View.GONE);
        //Go to the activity to display the joke
            startJokeActivity();
    }

    private void startJokeActivity() {
        Intent intent = new Intent(getActivity(), JokerActivity.class);

        intent.putExtra(getString(R.string.extra_joke),mJoke);
        startActivity(intent);
    }
}
