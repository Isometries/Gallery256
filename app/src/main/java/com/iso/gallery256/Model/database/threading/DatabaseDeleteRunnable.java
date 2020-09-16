package com.iso.gallery256.Model.database.threading;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iso.gallery256.Presenter.PhotoPresenter;

import java.security.InvalidKeyException;

public class DatabaseDeleteRunnable extends DatabaseRunnable {

    public DatabaseDeleteRunnable(PhotoPresenter photoPresenter, Uri uri, ContentResolver contentResolver, String albumName,
                               TextView progressText, LinearLayout ll, int jobSize, int complete)
    {
        super(photoPresenter, uri, contentResolver, albumName, progressText, ll, jobSize, complete);
    }

    @Override
    public void run() {
        presenter.photoDeleter(albumName, contentResolver);
    }
}
