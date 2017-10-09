package io.condor.condorify.implementation.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteban on 8/10/17.
 */

public class SpotifyArtists {
    @SerializedName("items")
    private List<SpotifyArtist> artists = new ArrayList<>();

    public List<SpotifyArtist> getArtists() {
        return artists;
    }
}
