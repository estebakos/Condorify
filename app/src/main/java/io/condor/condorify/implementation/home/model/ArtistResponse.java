package io.condor.condorify.implementation.home.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by esteban on 8/10/17.
 */

public class ArtistResponse {

    @SerializedName("artists")
    private SpotifyArtists artistList;

    public SpotifyArtists getArtistList() {
        return artistList;
    }
}
