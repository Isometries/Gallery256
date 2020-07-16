package com.iso.gallery256.View.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;

import com.iso.gallery256.R;
import com.iso.gallery256.View.presenters.PhotoPresenter;

public class AlbumView extends AppCompatActivity {

    PhotoPresenter presenter;
    PhotoLayoutFragment fragment;
    Button addAlbumButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);
        this.presenter = new PhotoPresenter(this);
        this.fragment = (PhotoLayoutFragment) getSupportFragmentManager().findFragmentById(R.id.photo_fragment);
        this.addAlbumButton = findViewById(R.id.album_add_button);

        this.addAlbumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                startActivityForResult(
                        Intent.createChooser(intent, "Complete action using"),
                        1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1:
                if (data != null) {
                    presenter.addAlbum(data, getContentResolver());
                }
                break;
        }
    }



}