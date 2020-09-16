package com.iso.gallery256.Model.database;

import android.content.Context;

import java.util.ArrayList;

import com.iso.gallery256.Model.Photo;

public class AlbumDatabase extends BaseDatabase {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "album.db";


    public AlbumDatabase(Context context)
    {
        super(context, DB_NAME, DB_VERSION);
    }

    @Override
    public ArrayList<Photo> getAlbums()
    {
        return getPhotos();
    }


    public void addAlbum(String name, String photoLocation, byte[] thumbnail, int albumID)
    {
        addPhoto(name, photoLocation, thumbnail, albumID);
    }

    public ArrayList<Photo> deleteAlbum(String albumName) {
        return deletePhotosbyName(albumName);
    }
}
