package com.iso.gallery256.Model.database.threading;

import android.content.ContentResolver;
import android.net.Uri;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iso.gallery256.Presenter.PhotoPresenter;

public abstract class DatabaseRunnable implements Runnable{
    PhotoPresenter presenter;
    Uri uri;
    ContentResolver contentResolver;
    String albumName;
    TextView progressText;
    LinearLayout ll;
    int jobSize, complete;

    public DatabaseRunnable(PhotoPresenter photoPresenter, Uri uri, ContentResolver contentResolver, String albumName,
                               TextView progressText, LinearLayout ll, int jobSize, int complete)
    {
        presenter = photoPresenter;
        this.uri = uri;
        this.contentResolver = contentResolver;
        this.albumName = albumName;
        this.progressText = progressText;
        this.jobSize = jobSize;
        this.complete = complete;
        this.ll = ll;
    }

    @Override
    public abstract void run();
}
