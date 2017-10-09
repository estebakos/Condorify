package io.condor.condorify.implementation.home.businessLogic;

import com.android.volley.VolleyError;

import java.util.List;

import io.condor.condorify.implementation.home.model.ArtistResponse;
import io.condor.condorify.implementation.home.model.SpotifyAlbum;

/**
 * Created by esteban on 8/10/17.
 */

public interface IHomeBusiness {
    void onError(VolleyError error);

    void onArtists(ArtistResponse artistResponse);

    void onAlbums(List<SpotifyAlbum> albums);
}
