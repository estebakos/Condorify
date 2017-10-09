package io.condor.condorify.implementation.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteban on 8/10/17.
 */

public class SpotifyAlbums {
    @SerializedName("items")
    private List<SpotifyAlbum> albums = new ArrayList<>();

    public List<SpotifyAlbum> getAlbums() {
        return albums;
    }
}
