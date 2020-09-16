package com.iso.gallery256.Model.database.threading;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iso.gallery256.Presenter.PhotoPresenter;

import java.security.InvalidKeyException;

public class DatabaseAddRunnable extends DatabaseRunnable {

    public DatabaseAddRunnable(PhotoPresenter photoPresenter, Uri uri, ContentResolver contentResolver, String albumName,
                               TextView progressText, LinearLayout ll, int jobSize, int complete)
    {
        super(photoPresenter, uri, contentResolver, albumName, progressText, ll, jobSize, complete);
    }

    @Override
    public void run() {
        try {
            progressText.post(new Runnable() {
                public void run() {
                    Log.i("RUNNABLE", "inside post " + complete);
                    progressText.setText(String.format("%d/%d", complete, jobSize));
                }
            });
            presenter.addPhoto(uri, albumName, contentResolver);
            if (complete + 1 == jobSize) {
                ll.setVisibility(View.INVISIBLE);
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
