package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Chris Hare on 1/8/2016.
 */
public class JokeFetchTest extends ApplicationTestCase<Application> implements EndpointListener {

    CountDownLatch latch = null;
    String joke = null;

    public JokeFetchTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        latch = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        latch.countDown();
    }

    public void testJokeFetch() {

        try {
            EndpointAsyncTask endpointTask = new EndpointAsyncTask();
            endpointTask.execute(this);
            latch.await(30, TimeUnit.SECONDS);

            assertNotNull("Joke was NULL", joke);
            assertFalse("Empty String", joke.length() == 0);

        } catch (Exception e) {
            fail("Exception in test!");
        }

    }

    @Override
    public void fetchJokeComplete(String joke) {
        this.joke = joke;
    }
}
