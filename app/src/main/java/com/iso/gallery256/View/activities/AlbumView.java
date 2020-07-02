package com.iso.gallery256.View.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.iso.gallery256.R;
import com.iso.gallery256.View.presenters.PhotoPresenter;

public class AlbumView extends AppCompatActivity {

    PhotoPresenter presenter;
    PhotoLayoutFragment fragment;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);
        this.presenter = new PhotoPresenter(this);
        this.fragment = (PhotoLayoutFragment) getSupportFragmentManager().findFragmentById(R.id.photo_fragment);
    }

}