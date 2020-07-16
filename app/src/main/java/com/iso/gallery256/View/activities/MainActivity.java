package com.iso.gallery256.View.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iso.gallery256.R;
import com.iso.gallery256.View.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity {

    private EditText password;
    private Button enterButton;
    private Button resetButton;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.password = (EditText) findViewById(R.id.password_field);
        this.enterButton =  (Button) findViewById(R.id.unlock_button);
        this.resetButton = (Button) findViewById(R.id.password_reset);
        this.presenter = new MainPresenter(this);

        //set buttons
        this.enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onItemClicked(enterButton);
            }
        });

        this.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onItemClicked(resetButton);
            }
        });

        //do something about this
//        String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
//        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
//        if (res != PackageManager.PERMISSION_GRANTED) {
//            this.finishAffinity();
//        }
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
    private void sendPassword()
    {
        presenter.sendPassword(password);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}