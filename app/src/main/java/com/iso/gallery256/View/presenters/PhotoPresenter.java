package com.iso.gallery256.View.presenters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.io.File;

import Model.FileHandler;
import Model.Utils;
import Model.database.AlbumDatabase;

public class PhotoPresenter implements PresenterBase {

     /* TO DO
        flesh-out all model classes
     */

    private Activity mainContext;
    private AlbumDatabase database; //must inherent from base  database
    private FileHandler fileHandler;

    public PhotoPresenter(Activity mainContext)
    {
        this.mainContext = mainContext;
        this.database = new AlbumDatabase(mainContext);
        this.fileHandler = new FileHandler(mainContext);
    }

//    public ArrayList<Photo> getPhotos()
//    {
//        //decrypt photos and populate the fragment
//        return database.getAlbums();
//    }

    //MUST BE THREADED
    @SuppressLint("NewApi")
    public void addAlbum(Intent data, ContentResolver contentResolver)
    {
        Uri imageUri = data.getData();
        File photoFile = Utils.getFilefromUri(imageUri, contentResolver, mainContext);

        byte[] imageArray = Utils.getBytesfromFile(photoFile);
        byte[] thumbnail = Utils.getThumbnailfromFile(imageArray);
        //add name prompt later
        database.addAlbum("TEST", "TEST", thumbnail, 1);
    }

    //MUST BE THREADED - ADD PHOTO TO ALBUM
    public void addPhoto(Intent data, ContentResolver contentResolver)
    {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onItemClicked(View v)
    {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Context getMainContext() {
        return null;
    }
}
