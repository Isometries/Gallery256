package com.iso.gallery256.View.presenters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;


import java.io.File;
import java.net.URI;
import java.security.InvalidKeyException;

import com.iso.gallery256.Crypto.EncryptionHelper;
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


    //MUST BE THREADED
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

    public void addPhoto(Intent data, String albumName,  ContentResolver contentResolver) throws InvalidKeyException
    {
        Uri imageUri = data.getData();
        File photoFile = Conversions.getFilefromUri(imageUri, contentResolver, mainContext);

        byte[] imageArray = Conversions.getBytesfromFile(photoFile);
        byte[] thumbnail = Conversions.getThumbnailfromFile(imageArray);


        File encryptedFile  = Conversions.createEncryptedPhoto(photoFile, mainContext);
        String fileName = Conversions.getFileNamefromURI(encryptedFile.toURI());
        Log.i("URI : ", fileName);
        byte[] encryptedThumbnail = cryptoStream.encryptByteArray(thumbnail, encryptedFile.toURI().toString());
        photoDatabase.addPhoto(albumName, encryptedFile.toURI().toString(), encryptedThumbnail, 1);
    }
}
