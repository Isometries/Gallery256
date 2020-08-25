package com.iso.gallery256.Presenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.File;
import java.net.URI;
import java.security.InvalidKeyException;
import java.util.ArrayList;

import com.iso.gallery256.Crypto.EncryptionHelper;
import com.iso.gallery256.Model.database.threading.DatabaseAddRunnable;
import com.iso.gallery256.R;
import com.iso.gallery256.Utils.Conversions;
import com.iso.gallery256.Model.database.AlbumDatabase;
import com.iso.gallery256.Model.database.PhotoDatabase;


public class PhotoPresenter {


    private Activity mainContext;
    private AlbumDatabase albumDatabase;
    private PhotoDatabase photoDatabase;
    private EncryptionHelper cryptoStream;

    public PhotoPresenter(Activity mainContext)
    {
        this.mainContext = mainContext;
        this.albumDatabase = new AlbumDatabase(mainContext);
        this.photoDatabase = new PhotoDatabase(mainContext);
        this.cryptoStream = new EncryptionHelper(mainContext);
    }


    @SuppressLint("NewApi")
    public void addAlbum(Intent data, ContentResolver contentResolver) throws InvalidKeyException
    {
        Uri imageUri = data.getData();
        File photoFile = Conversions.getFilefromUri(imageUri, contentResolver, mainContext);
        String name = Long.toString(albumDatabase.getSize());
        File encryptedFile  = Conversions.createEncryptedPhoto(photoFile, mainContext);
        String photoLocation = encryptedFile.toURI().toString();

//        String fileName = Conversions.getFileNamefromURI(URI.create(photoLocation));
        byte[] imageArray = Conversions.getBytesfromFile(photoFile);
        byte[] thumbnail = cryptoStream.encryptByteArray(Conversions.getThumbnailfromFile(imageArray), photoLocation);

        albumDatabase.addAlbum(name, photoLocation, thumbnail, 1);
        photoDatabase.addPhoto(name, photoLocation, thumbnail, 1);
    }

    public void addPhoto(Uri imageUri, String albumName,  ContentResolver contentResolver) throws InvalidKeyException
    {
        File photoFile = Conversions.getFilefromUri(imageUri, contentResolver, mainContext);

        byte[] imageArray = Conversions.getBytesfromFile(photoFile);
        byte[] thumbnail = Conversions.getThumbnailfromFile(imageArray);

        File encryptedFile  = Conversions.createEncryptedPhoto(photoFile, mainContext);
        String fileName = Conversions.getFileNamefromURI(encryptedFile.toURI());
        Log.i("URI : ", fileName);
        byte[] encryptedThumbnail = cryptoStream.encryptByteArray(thumbnail, encryptedFile.toURI().toString());

        photoDatabase.addPhoto(albumName, encryptedFile.toURI().toString(), encryptedThumbnail, 1);
    }

    public void addPhotos(Intent data, String albumName, ContentResolver contentResolver)
    {
        HandlerThread handlerThread = new HandlerThread("Database_add");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        if (data.getClipData() != null) {
            LinearLayout ll = (LinearLayout) mainContext.findViewById(R.id.llProgressBar);
            TextView progressText = ll.findViewById(R.id.pbText);

            ClipData clipData = data.getClipData();
            int jobSize = clipData.getItemCount();

            ll.setVisibility(View.VISIBLE);

            for (int i = 0; i < jobSize; i++) {
                ClipData.Item item = clipData.getItemAt(i);
                Uri uri = item.getUri();
                handler.post(new DatabaseAddRunnable(this, uri, contentResolver, albumName, progressText, ll, jobSize, i+1));
            }

        } else if (data.getData() != null) {
            try {
                addPhoto(data.getData(), albumName, contentResolver);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }


    }

}
