package com.iso.gallery256.View.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.iso.gallery256.R;
import com.iso.gallery256.View.presenters.PhotoZoomViewPresenter;

import java.net.URI;

public class PhotoZoomView extends AppCompatActivity {

    private PhotoZoomViewPresenter presenter;
    private PhotoView photoView;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_zoom_view);

        Intent intent = getIntent();
        location = intent.getStringExtra("location");
        presenter = new PhotoZoomViewPresenter(this, URI.create(location));
        photoView = (PhotoView) findViewById(R.id.photo_view);
        setPhoto();

    }

    protected void setPhoto()
    {
        photoView.setImageBitmap(presenter.getPhoto());
    }
}