package com.iso.gallery256.Model.database.threading;

import android.util.Log;

import com.iso.gallery256.Model.Photo;
import com.iso.gallery256.Model.database.BaseDatabase;

import java.util.ArrayList;

public class DatabaseQueryRunnable implements Runnable {

    private ArrayList<Photo> photos;
    BaseDatabase database;
    private String name = null;

    public DatabaseQueryRunnable(BaseDatabase database, ArrayList<Photo> photos, String name)
    {
        this.database = database;
        this.photos = photos;
        this.name = name;
    }

    @Override
    public void run() {
        Log.d("Threading", "Running...");
        ArrayList<Photo> tmp = new ArrayList<>();
        if (name != null) {
            tmp = database.getPhotos(name);

        } else {
            tmp = database.getAlbums();
        }
        photos.addAll(tmp);
        Log.d("Threading", "Finished");
    }
}
