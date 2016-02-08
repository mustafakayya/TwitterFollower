package com.kayya.twitterfollower.network;



import com.kayya.twitterfollower.models.FollowersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mustafakaya on 07/02/16.
 */
public interface TwitterService {
    @GET("1.1/followers/list.json?skip_status=true&include_user_entities=false")
    Call<FollowersResponse> getFollowers(@Query("cursor") String cursor,@Query("screen_name") String screen_name);
}
