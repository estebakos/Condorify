package io.condor.condorify.utilities;

/**
 * Created by esteban on 7/10/17.
 */

public class Constants {

    public static class Spotify {
        public static final int REQUEST_CODE = 1337;
        public static final String REDIRECT_URI = "http://condorify.io/callback/";
        public static final String GET_ARTIST_URL = "https://api.spotify.com/v1/search?q=";
        public static final String GET_ALBUMS_URL = "https://api.spotify.com/v1/artists/";
        public static final String GET_ARTIST_REQUEST_TYPE = "&type=artist";
        public static final String GET_ALBUMS_REQUEST_TYPE = "/albums";
    }

    public static class Preferences {
        public static final String SPOTIFY_TOKEN = "spotifyToken";
        static final String PREFERENCES_NAME = "CondorifyPreferences";
    }

    public static class HttpStatus {
        public static final int UNAUTHORIZED = 401;
    }

    public static class LaunchScreen {
        public static final long LAUNCH_SCREEN_TIME = 3000;
    }
}
