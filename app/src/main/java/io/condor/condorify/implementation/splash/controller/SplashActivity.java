package io.condor.condorify.implementation.splash.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import io.condor.condorify.R;
import io.condor.condorify.base.controller.BaseActivity;
import io.condor.condorify.implementation.home.controller.HomeActivity;
import io.condor.condorify.utilities.Constants;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                openMenuActivity();
            }
        }, Constants.LaunchScreen.LAUNCH_SCREEN_TIME);
    }

    private void openMenuActivity() {
        finish();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

    }
}