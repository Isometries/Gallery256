package Model.database;

import android.content.Context;

import java.util.ArrayList;

import Model.Photo;

public class AlbumDatabase extends BaseDatabase {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "album.db";


    public AlbumDatabase(Context context)
    {
        super(context, DB_NAME, DB_VERSION);
    }

    public ArrayList<Photo> getAlbums()
    {
        return getPhotos();
    }

    public void addAlbum(String name, String photoLocation, byte[] thumbnail, int albumID)
    {
        addPhoto(name, photoLocation, thumbnail, albumID);
    }

    public void deleteAlbum(){}//deal with deletion later
}
