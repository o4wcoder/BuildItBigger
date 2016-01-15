package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.text.style.TtsSpan;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.jokesbackend.myApi.MyApi;

import java.io.IOException;

import gradle.udacity.com.jokeractivity.JokerActivity;

/**
 * Created by Chris Hare on 1/9/2016.
 */
public class EndpointAsyncTask extends AsyncTask<EndpointListener, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    private EndpointListener mEnpointListener = null;



    @Override
    protected String doInBackground(EndpointListener ... params) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        mEnpointListener = params[0];

        try {
            // return myApiService.sayHi(name).execute().getData();
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
           // return e.getMessage();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
       // Intent intent = new Intent(context, JokerActivity.class);
        //intent.putExtra(EXTRA_JOKE,result);
        //context.startActivity(intent);

        if(this.mEnpointListener != null) {
            this.mEnpointListener.fetchJokeComplete(result);
        }
    }


}

interface EndpointListener {

    void fetchJokeComplete(String joke);
}
