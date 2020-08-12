package com.iso.gallery256.View.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.iso.gallery256.R;
import com.iso.gallery256.View.activities.AlbumView;
import com.iso.gallery256.View.activities.PhotoZoomView;

import java.net.URI;
import java.security.InvalidKeyException;
import java.util.ArrayList;

import com.iso.gallery256.Crypto.EncryptionHelper;
import com.iso.gallery256.Model.Photo;
import com.iso.gallery256.Utils.Conversions;

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
        CustomPhotoAdapter vh = new CustomPhotoAdapter(v, parent.getContext());
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        try {
            ((CustomPhotoAdapter) holder).setPhoto(photos.get(position));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public Photo getItem(int i)
    {
        return photos.get(i);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class CustomPhotoAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView cardView;
        private Photo photo;
        private Context context;
        private EncryptionHelper cryptoStream;

        public CustomPhotoAdapter(@NonNull View itemView, Context context)
        {
            super(itemView);
            cardView = itemView.findViewById(R.id.image);
            this.context = context;
            cardView.setOnClickListener(this);
            cryptoStream = new EncryptionHelper(context);
        }

        public void setPhoto(Photo photo) throws InvalidKeyException
        {
            byte[] ciphertext = photo.getThumbNail();
//            String fileName = Conversions.getFileNamefromURI(URI.create(photo.getPhotoLocation()));
            byte[] plaintext = cryptoStream.decryptByteArray(ciphertext, photo.getPhotoLocation());

            this.photo = photo;
            cardView.setImageBitmap(Conversions.BytestoBMP(plaintext));
        }

        @Override
        public void onClick(View v)
        {
            //also handle opening photo view
            if (context instanceof AlbumView) {
                Intent myIntent = new Intent(context, PhotoZoomView.class);
                myIntent.putExtra("location", photo.getPhotoLocation());
//                Log.d("HomeView Intent", context.getStringExtra("password"));
                myIntent.putExtra("password", ((Activity) context).getIntent().getStringExtra("password"));
                context.startActivity(myIntent);
            } else {
                Intent myIntent = new Intent(context, AlbumView.class);
                myIntent.putExtra("name", photo.getName()); //Optional parameters
                myIntent.putExtra("password", ((Activity) context).getIntent().getStringExtra("password"));
                Log.i("ALBUM NAME ", photo.getName());

                context.startActivity(myIntent);
            }
        }

    }
}
