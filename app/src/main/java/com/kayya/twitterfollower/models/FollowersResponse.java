package com.kayya.twitterfollower.models;

import java.util.List;

/**
 * Created by mustafakaya on 07/02/16.
 */
public class FollowersResponse {

    List<User> users;
    String next_cursor_str;
    String previous_cursor_str;

    public FollowersResponse() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public String getNext_cursor_str() {
        return next_cursor_str;
    }

    public void setNext_cursor_str(String next_cursor_str) {
        this.next_cursor_str = next_cursor_str;
    }



    public String getPrevious_cursor_str() {
        return previous_cursor_str;
    }

    public void setPrevious_cursor_str(String previous_cursor_str) {
        this.previous_cursor_str = previous_cursor_str;
    }
}
