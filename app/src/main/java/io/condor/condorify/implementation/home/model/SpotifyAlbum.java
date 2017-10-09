package io.condor.condorify.implementation.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteban on 8/10/17.
 */

public class SpotifyAlbum {

    private String id;
    private String name;
    private List<SpotifyImage> images = new ArrayList<>();

    @SerializedName("external_urls")
    private SpotifyExternalUrls externalUrls;

    @SerializedName("available_markets")
    private List<String> availableCountries = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SpotifyImage> getImages() {
        return images;
    }

    public SpotifyExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public List<String> getAvailableCountries() {
        return availableCountries;
    }
}
