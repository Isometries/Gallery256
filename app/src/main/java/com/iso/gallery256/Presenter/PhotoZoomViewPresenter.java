package com.iso.gallery256.Presenter;

import android.content.Context;
import android.graphics.Bitmap;

import java.net.URI;

import com.iso.gallery256.Crypto.EncryptionHelper;
import com.iso.gallery256.Utils.Conversions;
import com.iso.gallery256.Utils.FileHandler;

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
