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
import com.iso.gallery256.View.adapters.PhotoAdapter;


public class FragmentPresenter {

    private AlbumDatabase albumDatabase; //must inherent from base  database
    private PhotoDatabase photoDatabase;
    private HandlerThread handlerThread;
    private PhotoAdapter adapter;

    public FragmentPresenter(Fragment mainFragment, PhotoAdapter adapter)
    {
        this.handlerThread = new HandlerThread("Database");
        handlerThread.start();
        this.albumDatabase = new AlbumDatabase(mainFragment.getContext());
        this.photoDatabase = new PhotoDatabase(mainFragment.getContext());
        this.adapter = adapter;
    }

    public void getPhotos(Context context, String name, ArrayList<Photo> photos)
    {
        Handler handler = new Handler(handlerThread.getLooper());

        if (context instanceof HomeView){
            handler.post(new DatabaseQueryRunnable(albumDatabase, adapter, photos, null));

        } else {
            handler.post(new DatabaseQueryRunnable(photoDatabase, adapter, photos, name));
        }

    }

    //set up refresh method




}
