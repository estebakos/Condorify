package io.condor.condorify.implementation.home.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by esteban on 8/10/17.
 */

public class SpotifyExternalUrls {
    @SerializedName("spotify")
    private String url;

    public String getUrl() {
        return url;
    }
}
