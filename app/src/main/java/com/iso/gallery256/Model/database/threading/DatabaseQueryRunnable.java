package com.iso.gallery256.Model.database.threading;

import android.app.Activity;
import android.util.Log;

import com.iso.gallery256.Model.Photo;
import com.iso.gallery256.Model.database.BaseDatabase;
import com.iso.gallery256.adapters.PhotoAdapter;

import java.util.ArrayList;

public class DatabaseQueryRunnable implements Runnable {

    private ArrayList<Photo> photos;
    BaseDatabase database;
    private String name = null;
    private PhotoAdapter adapter;

    public DatabaseQueryRunnable(BaseDatabase database, PhotoAdapter adapter, ArrayList<Photo> photos, String name)
    {
        this.database = database;
        this.photos = photos;
        this.name = name;
        this.adapter = adapter;
    }

    @Override
    public void run() {
        Log.d("Threading", "Running...");
        ArrayList<Photo> tmp;
        if (name != null) {
            Log.d("Threading", name);
            tmp = database.getPhotos(name);
        } else {
            tmp = database.getAlbums();
        }
        photos.addAll(tmp);
        Log.d("Threading", "Finished");
        ((Activity) adapter.getFragment().getContext()).runOnUiThread(new Runnable() {
            public void run() {
                Log.d("THREADING", "Changing data");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
