package com.kayya.twitterfollower.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mustafakaya on 07/02/16.
 */
public class User implements Parcelable{


    String id_str;
    String name;
    String screen_name;
    String location;
    String url;
    String description;
    int followers_count;
    int friends_count;
    String profile_background_color;
    String profile_image_url;
    String profile_image_url_https;
    String profile_banner_url;
    boolean profile_use_background_image;

    public User() {
    }


    protected User(Parcel in) {
        id_str = in.readString();
        name = in.readString();
        screen_name = in.readString();
        location = in.readString();
        url = in.readString();
        description = in.readString();
        followers_count = in.readInt();
        friends_count = in.readInt();
        profile_background_color = in.readString();
        profile_image_url = in.readString();
        profile_image_url_https = in.readString();
        profile_banner_url = in.readString();
        profile_use_background_image = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public String getProfile_background_color() {
        return profile_background_color;
    }

    public void setProfile_background_color(String profile_background_color) {
        this.profile_background_color = profile_background_color;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getProfile_image_url_https() {
        return profile_image_url_https;
    }

    public void setProfile_image_url_https(String profile_image_url_https) {
        this.profile_image_url_https = profile_image_url_https;
    }


    public String getProfile_banner_url() {
        return profile_banner_url;
    }

    public void setProfile_banner_url(String profile_banner_url) {
        this.profile_banner_url = profile_banner_url;
    }

    public boolean isProfile_use_background_image() {
        return profile_use_background_image;
    }

    public void setProfile_use_background_image(boolean profile_use_background_image) {
        this.profile_use_background_image = profile_use_background_image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_str);
        dest.writeString(name);
        dest.writeString(screen_name);
        dest.writeString(location);
        dest.writeString(url);
        dest.writeString(description);
        dest.writeInt(followers_count);
        dest.writeInt(friends_count);
        dest.writeString(profile_background_color);
        dest.writeString(profile_image_url);
        dest.writeString(profile_image_url_https);
        dest.writeString(profile_banner_url);
        dest.writeByte((byte) (profile_use_background_image ? 1 : 0));
    }
}
