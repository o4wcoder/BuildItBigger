package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import gradle.udacity.com.jokeractivity.JokerActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointListener{

    private InterstitialAd mInterstitialAd;
    private Button mJokeButton;
    private String mJoke;
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.ad_id_test));

        //Create ad request.
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        //Populate ad request builder
        adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        //Start loadging the ad so it's ready
        mInterstitialAd.loadAd(adRequestBuilder.build());
        //Set Ad Listener
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });

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

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
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
        //Make sure we have the joke before starting ad.
        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        else {
            //Ad not loaded, just go to the activity to display the joke
            startJokeActivity();
        }
    }

    private void startJokeActivity() {
        Intent intent = new Intent(getActivity(), JokerActivity.class);

        intent.putExtra(getString(R.string.extra_joke),mJoke);
        startActivity(intent);
    }
}
