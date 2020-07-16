package com.iso.gallery256.View.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.iso.gallery256.R;

import java.util.ArrayList;

import Model.Photo;
import Model.Utils;

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Fragment fragment;
    ArrayList<Photo> photos;

    public PhotoAdapter(Fragment fragment, ArrayList<Photo> photos)
    {
        this.fragment = fragment;
        this.photos = photos;
        fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) //sety size of cards here
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        CustomPhotoAdapter vh = new CustomPhotoAdapter(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ((CustomPhotoAdapter) holder).setPhoto(photos.get(position));
    }

    public Photo getItem(int i)
    {
        return photos.get(i);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    //textview for testing
    public static class CustomPhotoAdapter extends RecyclerView.ViewHolder {

        private ImageView cardView;
        public CustomPhotoAdapter(@NonNull View itemView)
        {
            super(itemView);
//            layout = fragment.getView().findViewById(R.id.card_view);
            cardView = itemView.findViewById(R.id.image);
        }

        public void setPhoto(Photo photo)
        {
            byte[] imageBytes = photo.getThumbNail();
            cardView.setImageBitmap(Utils.BytestoBMP(imageBytes));
        }


    }
}
