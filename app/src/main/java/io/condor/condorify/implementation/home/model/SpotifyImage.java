package io.condor.condorify.implementation.home.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by esteban on 8/10/17.
 */

public class SpotifyImage {

    private int height;
    private int width;
    private String url;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
