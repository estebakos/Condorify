package io.condor.condorify.implementation.home.controller;

import java.util.List;

import io.condor.condorify.implementation.home.model.SpotifyAlbum;
import io.condor.condorify.implementation.home.model.SpotifyArtist;

/**
 * Created by esteban on 8/10/17.
 */

public interface IHomeUI {
    void onError(String message);

    void onUnauthorized();

    void onArtistNotFound();

    void onArtist(SpotifyArtist spotifyArtist);

    void onAlbums(List<SpotifyAlbum> albums);
}
