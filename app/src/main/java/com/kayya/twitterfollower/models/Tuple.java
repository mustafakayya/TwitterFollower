package com.kayya.twitterfollower.models;

/**
 * Created by mustafakaya on 08/02/16.
 */
public class Tuple {
    String key;
    String value;

    public Tuple(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
