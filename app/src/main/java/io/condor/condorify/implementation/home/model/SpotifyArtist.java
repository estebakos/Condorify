package io.condor.condorify.implementation.home.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteban on 8/10/17.
 */

public class SpotifyArtist {

    private String id;
    private String name;
    private SpotifyFollowers followers;
    private List<SpotifyImage> images = new ArrayList<>();
    private int popularity;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SpotifyFollowers getFollowers() {
        return followers;
    }

    public List<SpotifyImage> getImages() {
        return images;
    }

    public int getPopularity() {
        return popularity;
    }
}
