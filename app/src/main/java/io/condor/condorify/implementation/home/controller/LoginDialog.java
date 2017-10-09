package io.condor.condorify.implementation.home.controller;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import io.condor.condorify.R;

public class LoginDialog extends Dialog {

    public Button btnLogin;

    public LoginDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_login);
        if (getWindow() != null) {
            getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }

        setCanceledOnTouchOutside(false);
        setCancelable(false);
        btnLogin = findViewById(R.id.btnSpotifyLogin);
    }
}