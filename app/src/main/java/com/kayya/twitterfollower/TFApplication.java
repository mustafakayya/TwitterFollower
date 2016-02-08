package com.kayya.twitterfollower;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by mustafakaya on 08/02/16.
 */
public class TFApplication extends Application {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    public static final String TWITTER_KEY = "tKOfIPIwfAilWV9fp06P6Wthf";
    public static final String TWITTER_SECRET = "m9OkfXh7X06g9gXOau15yweFjOlP29zI3SU1bcyJGzqFhuhbvI";

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }
}
