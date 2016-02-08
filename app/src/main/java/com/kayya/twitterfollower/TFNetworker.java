package com.kayya.twitterfollower;




import com.kayya.twitterfollower.network.TwitterService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by mustafakaya on 06/02/16.
 */
public class TFNetworker {

    static Retrofit retrofitInstance;


    public static void init(String authToken,String authTokenSecret){

        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(TFApplication.TWITTER_KEY, TFApplication.TWITTER_SECRET);
        consumer.setTokenWithSecret(authToken, authTokenSecret);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();
        retrofitInstance = new Retrofit.Builder()
                .baseUrl("https://api.twitter.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


    public static TwitterService getInstance() {
        return retrofitInstance.create(TwitterService.class);
    }


}
