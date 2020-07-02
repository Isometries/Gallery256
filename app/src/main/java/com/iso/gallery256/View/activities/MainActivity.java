package com.iso.gallery256.View.activities;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    private void sendPassword()
    {
        presenter.sendPassword(password);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}