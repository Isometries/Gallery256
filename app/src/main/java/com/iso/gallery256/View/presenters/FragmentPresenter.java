package com.iso.gallery256.View.presenters;



import android.content.Context;

import androidx.fragment.app.Fragment;


import com.iso.gallery256.View.activities.HomeView;

import java.util.ArrayList;

import Model.Photo;
import Model.database.AlbumDatabase;
import Model.database.PhotoDatabase;


public class FragmentPresenter {

    private Fragment mainFragment;
    private AlbumDatabase albumDatabase; //must inherent from base  database
    private PhotoDatabase photoDatabase;


    public FragmentPresenter(Fragment mainFragment)
    {
        this.mainFragment = mainFragment;
        this.albumDatabase = new AlbumDatabase(mainFragment.getContext());
        this.photoDatabase = new PhotoDatabase(mainFragment.getContext());
    }

    public ArrayList<Photo> getPhotos(Context context, String name)
    {
        if (context instanceof HomeView){
            return albumDatabase.getAlbums();
        } else {
            return photoDatabase.getPhotos(name);
        }

    }

    //set up refresh method




}
