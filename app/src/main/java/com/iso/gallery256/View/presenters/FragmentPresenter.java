package com.iso.gallery256.View.presenters;



import androidx.fragment.app.Fragment;

import com.iso.gallery256.View.adapters.PhotoAdapter;

import java.util.ArrayList;

import Model.Photo;


public class FragmentPresenter {

    private Fragment mainFragment;
    private PhotoAdapter photoAdapter;

    public FragmentPresenter(Fragment mainFragment)
    {
        this.mainFragment = mainFragment;
//        this.photoAdapter = new PhotoAdapter(mainFragment); //later pass photos to this adapter
    }

    private ArrayList<Photo> fetchPhotos()
    {
        //TODO add logic to decrpt and fetch photos depending on album or photo viwe - must add check mainFragment context ID
        return new ArrayList<Photo>();
    }



}
