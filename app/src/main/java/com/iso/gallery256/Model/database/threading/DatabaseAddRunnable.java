package com.iso.gallery256.Model.database.threading;

import android.content.ContentResolver;
import android.net.Uri;

import com.iso.gallery256.Presenter.PhotoPresenter;

import java.security.InvalidKeyException;
import java.util.ArrayList;

public class DatabaseAddRunnable implements Runnable {

    PhotoPresenter presenter;
    Uri uri;
    ContentResolver contentResolver;
    String albumName;

    public DatabaseAddRunnable(PhotoPresenter photoPresenter, Uri uri, ContentResolver contentResolver, String albumName)
    {
        presenter = photoPresenter;
        this.uri = uri;
        this.contentResolver = contentResolver;
        this.albumName = albumName;
    }

    @Override
    public void run() {
        try {
            presenter.addPhoto(uri, albumName, contentResolver);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
