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

public class HomeView extends AppCompatActivity {

    PhotoPresenter presenter;
    PhotoDisplayFragment fragment;
    Button addAlbumButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);

        this.presenter = new PhotoPresenter(this);
        this.fragment = (PhotoDisplayFragment) getSupportFragmentManager().findFragmentById(R.id.photo_fragment1);
        this.addAlbumButton = findViewById(R.id.album_add_button);
        Log.d("HomeView Intent", getIntent().getStringExtra("password"));
        this.addAlbumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HomeView", "Activated onClick");
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("HomeView", "Activated onActivity");
        switch (requestCode) {

            case 1:
                if (data != null) {
                    try {
                        presenter.addAlbum(data, getContentResolver());
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}