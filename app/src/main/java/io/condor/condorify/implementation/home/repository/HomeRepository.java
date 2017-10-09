package io.condor.condorify.implementation.home.repository;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.condor.condorify.base.repository.BaseVolleyRequestQueue;
import io.condor.condorify.implementation.home.businessLogic.IHomeBusiness;
import io.condor.condorify.implementation.home.model.ArtistResponse;
import io.condor.condorify.implementation.home.model.SpotifyAlbum;
import io.condor.condorify.utilities.Constants;
import io.condor.condorify.utilities.PreferencesManager;

/**
 * Created by esteban on 8/10/17.
 */

public class HomeRepository {

    private IHomeBusiness iHomeBusiness;

    public HomeRepository(IHomeBusiness iHomeBusiness) {
        this.iHomeBusiness = iHomeBusiness;
    }

    public void getArtist(final Context context, String strQuery) {
        String getArtistUrl = Constants.Spotify.GET_ARTIST_URL + strQuery.replace(" ", "+") + Constants.Spotify.GET_ARTIST_REQUEST_TYPE;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getArtistUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArtistResponse artistResponse = new Gson().fromJson(response.toString(), ArtistResponse.class);
                iHomeBusiness.onArtists(artistResponse);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iHomeBusiness.onError(error);
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + PreferencesManager.getString
                        (context, Constants.Preferences.SPOTIFY_TOKEN));
                return params;
            }
        };

        BaseVolleyRequestQueue.getInstance(context).getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
        BaseVolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getAlbums(final Context context, String artistId) {
        String getArtistUrl = Constants.Spotify.GET_ALBUMS_URL + artistId + Constants.Spotify.GET_ALBUMS_REQUEST_TYPE;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getArtistUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    List<SpotifyAlbum> spotifyAlbums = new
                            Gson().fromJson(response.getJSONArray("items").toString(), new TypeToken<List<SpotifyAlbum>>() {
                    }.getType());
                    iHomeBusiness.onAlbums(spotifyAlbums);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iHomeBusiness.onError(error);
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + PreferencesManager.getString
                        (context, Constants.Preferences.SPOTIFY_TOKEN));
                return params;
            }
        };

        BaseVolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
