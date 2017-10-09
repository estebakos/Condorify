package io.condor.condorify.implementation.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import io.condor.condorify.R;
import io.condor.condorify.implementation.home.controller.IAlbum;
import io.condor.condorify.implementation.home.model.SpotifyAlbum;

public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SpotifyAlbum> albums = new ArrayList<>();
    private Context context;
    private IAlbum iAlbum;

    public AlbumsAdapter(Context context, List<SpotifyAlbum> albums, IAlbum iAlbum) {
        this.context = context;
        this.albums = albums;
        this.iAlbum = iAlbum;
    }

    public List<SpotifyAlbum> getAlbums() {
        return albums;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_album, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CustomViewHolder customViewHolder = (CustomViewHolder) holder;
        final SpotifyAlbum album = albums.get(position);
        defineTagContainerAttributes(customViewHolder.tagContainerAlbum);

        customViewHolder.tvAlbumName.setText(album.getName());

        if (!album.getImages().isEmpty()) {
            Glide.with(context).load(album.getImages().get(0).getUrl())
                    .thumbnail(Glide.with(context).load(R.drawable.preloader))
                    .animate(R.anim.slide_left_in).crossFade().into(customViewHolder.ivAlbum);
        }

        if (!album.getAvailableCountries().isEmpty() && album.getAvailableCountries().size() < 5) {
            customViewHolder.tagContainerAlbum.setVisibility(View.VISIBLE);
            customViewHolder.tagContainerAlbum.setTags(album.getAvailableCountries());
        } else {
            customViewHolder.tagContainerAlbum.setVisibility(View.GONE);
        }


        customViewHolder.rlAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAlbum.onAlbumSelected(album);
            }
        });
    }

    private void defineTagContainerAttributes(TagContainerLayout tagContainer) {
        tagContainer.setBorderColor(Color.TRANSPARENT);
        tagContainer.setIsTagViewClickable(false);
        tagContainer.setBackgroundColor(Color.TRANSPARENT);
        tagContainer.setBorderWidth(0);
        tagContainer.setTagTextColor(Color.WHITE);
        tagContainer.setTagBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        tagContainer.setTheme(ColorFactory.NONE);
        tagContainer.setTagMaxLength(30);
        tagContainer.setTagBorderWidth(1.5f);
        tagContainer.setTagBorderColor(context.getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public int getItemCount() {
        //return list products
        return albums.size();
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAlbumName;
        private ImageView ivAlbum;
        private RelativeLayout rlAlbum;
        private TagContainerLayout tagContainerAlbum;

        CustomViewHolder(View view) {
            super(view);
            tvAlbumName = view.findViewById(R.id.tvAlbumName);
            ivAlbum = view.findViewById(R.id.ivAlbumCover);
            rlAlbum = view.findViewById(R.id.rlAlbum);
            tagContainerAlbum = view.findViewById(R.id.tagContainerAlbum);
        }
    }
}