package com.iso.gallery256.View.presenters;

import android.content.Context;
import android.graphics.Bitmap;

import com.iso.gallery256.View.activities.PhotoZoomView;

import java.net.URI;

import Crypto.EncryptionHelper;
import Utils.Conversions;
import Utils.FileHandler;

public class PhotoZoomViewPresenter {

    private Context context;
    private URI uri;
    private EncryptionHelper cryptoStream;
    private FileHandler fileHandler;

    public PhotoZoomViewPresenter(Context context, URI uri)
    {
        this.context = context;
        this.uri = uri;
        this.cryptoStream = new EncryptionHelper(context);
        this.fileHandler = new FileHandler(context);
    }

    public Bitmap getPhoto()
    {
        byte[] plaintext = fileHandler.decryptURI(uri);
        return Conversions.BytestoBMP(plaintext);
    }
}
