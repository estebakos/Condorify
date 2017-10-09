package io.condor.condorify.implementation.home.controller;

import io.condor.condorify.implementation.home.model.SpotifyAlbum;

/**
 * Created by esteban on 8/10/17.
 */

public interface IAlbum {
    void onAlbumSelected(SpotifyAlbum spotifyAlbum);
}
