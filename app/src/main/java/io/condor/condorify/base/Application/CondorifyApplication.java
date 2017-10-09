package io.condor.condorify.base.Application;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by esteban on 8/10/17.
 */

public class CondorifyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
