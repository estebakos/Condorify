package io.condor.condorify.implementation.home.businessLogic;

import android.content.Context;

import com.android.volley.VolleyError;

import java.util.List;

import io.condor.condorify.R;
import io.condor.condorify.implementation.home.controller.HomeActivity;
import io.condor.condorify.implementation.home.controller.IHomeUI;
import io.condor.condorify.implementation.home.model.AlbumResponse;
import io.condor.condorify.implementation.home.model.ArtistResponse;
import io.condor.condorify.implementation.home.model.SpotifyAlbum;
import io.condor.condorify.implementation.home.repository.HomeRepository;
import io.condor.condorify.utilities.Constants;

/**
 * Created by esteban on 8/10/17.
 */

public class HomeBusiness implements IHomeBusiness {

    private IHomeUI iHomeUI;
    private HomeRepository homeRepository;
    private Context context;

    public HomeBusiness(IHomeUI iHomeUI, Context context) {
        this.iHomeUI = iHomeUI;
        this.homeRepository = new HomeRepository(this);
    }

    public void getArtist(Context context, String query) {
        homeRepository.getArtist(context, query);
    }

    public void getAlbums(Context context, String artistId) {
        homeRepository.getAlbums(context, artistId);
    }

    @Override
    public void onError(VolleyError error) {
        if (error.networkResponse != null) {
            iHomeUI.onUnauthorized();
        } else {
            iHomeUI.onError(context.getString(R.string.str_unknown_error));
        }
    }

    @Override
    public void onArtists(ArtistResponse artistResponse) {
        if (artistResponse.getArtistList().getArtists().isEmpty()) {
            iHomeUI.onArtistNotFound();
        } else {
            iHomeUI.onArtist(artistResponse.getArtistList().getArtists().get(0));
        }
    }

    @Override
    public void onAlbums(List<SpotifyAlbum> albums) {
        iHomeUI.onAlbums(albums);
    }

}
