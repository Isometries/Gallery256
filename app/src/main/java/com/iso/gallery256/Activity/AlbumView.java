package com.iso.gallery256.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iso.gallery256.R;
import com.iso.gallery256.Presenter.PhotoPresenter;

import java.security.InvalidKeyException;

public class AlbumView extends AppCompatActivity {

    PhotoPresenter presenter;
    PhotoDisplayFragment fragment;
    Button addPhotoButton;
    String albumName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        Log.d("AlbumView Intent", getIntent().getStringExtra("password"));
        this.presenter = new PhotoPresenter(this);
        this.fragment = (PhotoDisplayFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_photo);
        addPhotoButton = (Button) findViewById(R.id.addPhoto);

        Intent intent = getIntent();
        albumName = intent.getStringExtra("name");

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AlbumView onClick", "Activated in onClick");
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
        Log.d("AlbumView onClick", "Activated in onActivityResult");
        switch (requestCode) {

            case 1:
                if (data != null) {
                    try {
                        presenter.addPhoto(data, albumName,  getContentResolver());
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

}

