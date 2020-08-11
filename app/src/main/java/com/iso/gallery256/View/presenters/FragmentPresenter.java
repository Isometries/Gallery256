package com.iso.gallery256.View.presenters;



import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import androidx.fragment.app.Fragment;


import com.iso.gallery256.Model.database.threading.DatabaseQueryRunnable;
import com.iso.gallery256.View.activities.HomeView;

import java.util.ArrayList;

import com.iso.gallery256.Model.Photo;
import com.iso.gallery256.Model.database.AlbumDatabase;
import com.iso.gallery256.Model.database.PhotoDatabase;


public class FragmentPresenter {

    private AlbumDatabase albumDatabase; //must inherent from base  database
    private PhotoDatabase photoDatabase;
    private HandlerThread handlerThread;

    public FragmentPresenter(Fragment mainFragment)
    {
        this.handlerThread = new HandlerThread("Database");
        handlerThread.start();
        this.albumDatabase = new AlbumDatabase(mainFragment.getContext());
        this.photoDatabase = new PhotoDatabase(mainFragment.getContext());
    }

    public ArrayList<Photo> getPhotos(Context context, String name)
    {
        Handler handler = new Handler(handlerThread.getLooper());

        if (context instanceof HomeView){
            ArrayList<Photo> albums = new ArrayList<>();
            handler.post(new DatabaseQueryRunnable(albumDatabase, albums, null));

            return albums;
        } else {
            ArrayList<Photo> photos = new ArrayList<>();
            handler.post(new DatabaseQueryRunnable(photoDatabase, photos, name));

            return photos;
        }

    }

    //set up refresh method




}
