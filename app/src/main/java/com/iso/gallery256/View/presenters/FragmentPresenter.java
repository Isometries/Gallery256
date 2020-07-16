package com.iso.gallery256.View.presenters;



import androidx.fragment.app.Fragment;


import java.util.ArrayList;

import Model.Photo;
import Model.database.AlbumDatabase;


public class FragmentPresenter {

    private Fragment mainFragment;
    private AlbumDatabase database; //must inherent from base  database


    public FragmentPresenter(Fragment mainFragment)
    {
        this.mainFragment = mainFragment;
        this.database = new AlbumDatabase(mainFragment.getContext());
    }

    public ArrayList<Photo> getPhotos()
    {
        //TODO add logic to decrpt and fetch photos depending on album or photo viwe - must add check mainFragment context ID

        return database.getAlbums();
    }

    //set up refresh method




}
