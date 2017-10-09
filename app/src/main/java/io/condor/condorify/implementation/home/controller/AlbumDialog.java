package io.condor.condorify.implementation.home.controller;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.condor.condorify.R;

public class AlbumDialog extends Dialog {

    public Button btnOpenSpotify;
    private TextView tvAlbumTitle;
    private ImageView ivAlbumImage;

    public AlbumDialog(@NonNull Context context) {
        super(context);
    }

    public void setTitleAlbum(String name) {
        tvAlbumTitle.setText(name);
    }

    public void setAlbumImage(String image) {
        Glide.with(getContext()).load(image).into(ivAlbumImage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_album);
        if (getWindow() != null) {
            getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }

        setCanceledOnTouchOutside(true);
        setCancelable(true);
        btnOpenSpotify = findViewById(R.id.btnOpenSpotify);
        tvAlbumTitle = findViewById(R.id.tvAlbumTitle);
        ivAlbumImage = findViewById(R.id.ivAlbumImage);
    }
}