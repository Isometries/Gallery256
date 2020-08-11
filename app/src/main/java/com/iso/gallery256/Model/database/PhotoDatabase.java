package com.iso.gallery256.Model.database;

import android.content.Context;

import com.iso.gallery256.Model.Photo;

import java.util.ArrayList;

public class PhotoDatabase extends BaseDatabase {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "photo.db";

    public PhotoDatabase(Context context)
    {
        super(context, DB_NAME, DB_VERSION);
    }

    @Override
    public ArrayList<Photo> getAlbums() {
        return null;
    }
}
