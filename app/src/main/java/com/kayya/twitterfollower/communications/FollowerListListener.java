package com.kayya.twitterfollower.communications;


import com.kayya.twitterfollower.models.User;

/**
 * Created by mustafakaya on 08/02/16.
 */
public interface FollowerListListener {
    public void onFollowerSelected(User follower);
}
