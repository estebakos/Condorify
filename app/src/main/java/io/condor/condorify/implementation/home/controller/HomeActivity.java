package io.condor.condorify.implementation.home.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daasuu.ahp.AnimateHorizontalProgressBar;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.util.List;

import io.condor.condorify.BuildConfig;
import io.condor.condorify.R;
import io.condor.condorify.base.controller.BaseActivity;
import io.condor.condorify.implementation.home.adapter.AlbumsAdapter;
import io.condor.condorify.implementation.home.businessLogic.HomeBusiness;
import io.condor.condorify.implementation.home.model.SpotifyAlbum;
import io.condor.condorify.implementation.home.model.SpotifyArtist;
import io.condor.condorify.utilities.AppBarStateChangeListener;
import io.condor.condorify.utilities.Constants;
import io.condor.condorify.utilities.PreferencesManager;

/**
 * Created by esteban on 8/10/17.
 */

public class HomeActivity extends BaseActivity implements IHomeUI, SearchView.OnQueryTextListener, IAlbum {

    private HomeBusiness homeBusiness;
    private String strLastSearch;
    private CoordinatorLayout clArtistInfo;
    private ImageView ivArtistCover;
    private TextView tvArtistName, tvFollowers;
    private AnimateHorizontalProgressBar pbPopularity;
    private CollapsingToolbarLayout ctArtist;
    private Toolbar toolbarArtist;
    private RecyclerView rvAlbums;
    private LinearLayout lySearchArtists, lyArtistNotfFound;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.homeBusiness = new HomeBusiness(this, this);
        validateToken();
        bindViews();
    }

    private void bindViews() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        //Artist Info
        clArtistInfo = (CoordinatorLayout) findViewById(R.id.clArtistInfo);
        tvArtistName = (TextView) findViewById(R.id.tvArtistName);
        tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        pbPopularity = (AnimateHorizontalProgressBar) findViewById(R.id.pbPopularity);
        ivArtistCover = (ImageView) findViewById(R.id.ivArtistCover);
        ctArtist = (CollapsingToolbarLayout) findViewById(R.id.ctArtist);
        lySearchArtists = (LinearLayout) findViewById(R.id.lySearchArtists);
        lyArtistNotfFound = (LinearLayout) findViewById(R.id.lyArtistNotFound);
        toolbarArtist = (Toolbar) findViewById(R.id.toolbarArtist);

        AppBarLayout appBarLayoutArtist = (AppBarLayout) findViewById(R.id.appBarLayoutArtist);
        appBarLayoutArtist.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state) {
                    case COLLAPSED:
                        toolbarArtist.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        break;
                    default:
                        toolbarArtist.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                }
            }
        });

        //Albums Info
        rvAlbums = (RecyclerView) findViewById(R.id.rvAlbums);
        rvAlbums.setHasFixedSize(true);
        rvAlbums.setLayoutManager(new LinearLayoutManager(this));
    }

    private void validateToken() {
        if (PreferencesManager.getString(this, Constants.Preferences.SPOTIFY_TOKEN) == null ||
                PreferencesManager.getString(this, Constants.Preferences.SPOTIFY_TOKEN).isEmpty()) {
            openLoginDialog();
        }
    }

    private void openLoginDialog() {
        final LoginDialog loginDialog = new LoginDialog(this);
        loginDialog.show();
        loginDialog.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                loginDialog.cancel();
            }
        });
    }

    private void putArtistInfo(SpotifyArtist spotifyArtist) {
        tvArtistName.setText(spotifyArtist.getName());
        ctArtist.setTitle(spotifyArtist.getName());
        tvFollowers.setText(String.valueOf(spotifyArtist.getFollowers().getTotal()));
        pbPopularity.setProgressWithAnim(spotifyArtist.getPopularity());

        if (!spotifyArtist.getImages().isEmpty()) {
            Glide.with(this).load(spotifyArtist.getImages().get(0).getUrl()).into(ivArtistCover);
        }
    }

    private void login() {
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(BuildConfig.SPOTIFY_CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                Constants.Spotify.REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, Constants.Spotify.REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.Spotify.REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                PreferencesManager.saveString(this, Constants.Preferences.SPOTIFY_TOKEN, response.getAccessToken());
                if (strLastSearch != null) {
                    homeBusiness.getArtist(this, strLastSearch);
                }
            } else {
                openLoginDialog();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(this);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (query.length() >= 3) {
            strLastSearch = query;
            homeBusiness.getArtist(this, query);
        }
        return false;
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        clArtistInfo.setVisibility(View.GONE);
        lySearchArtists.setVisibility(View.VISIBLE);
        lyArtistNotfFound.setVisibility(View.GONE);
    }

    @Override
    public void onUnauthorized() {
        login();
    }

    @Override
    public void onArtistNotFound() {
        clArtistInfo.setVisibility(View.GONE);
        lySearchArtists.setVisibility(View.GONE);
        lyArtistNotfFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void onArtist(SpotifyArtist spotifyArtist) {
        clArtistInfo.setVisibility(View.VISIBLE);
        lySearchArtists.setVisibility(View.GONE);
        lyArtistNotfFound.setVisibility(View.GONE);

        homeBusiness.getAlbums(this, spotifyArtist.getId());
        putArtistInfo(spotifyArtist);
    }

    @Override
    public void onAlbums(List<SpotifyAlbum> albums) {
        rvAlbums.setAdapter(new AlbumsAdapter(this, albums, this));
    }

    @Override
    public void onAlbumSelected(final SpotifyAlbum spotifyAlbum) {
        AlbumDialog albumDialog = new AlbumDialog(this);
        albumDialog.show();
        albumDialog.setTitleAlbum(spotifyAlbum.getName());
        if (!spotifyAlbum.getImages().isEmpty()) {
            albumDialog.setAlbumImage(spotifyAlbum.getImages().get(0).getUrl());
        }
        albumDialog.btnOpenSpotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spotifyAlbum.getExternalUrls() != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(spotifyAlbum.getExternalUrls().getUrl()));
                    startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
