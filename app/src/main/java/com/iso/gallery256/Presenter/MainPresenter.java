package com.iso.gallery256.Presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.iso.gallery256.R;
import com.iso.gallery256.Activity.HomeView;
import com.iso.gallery256.Activity.MainActivity;

public class MainPresenter implements PresenterBase {

     /* TO DO
        flesh-out all model classes
     */

    private MainActivity mainContext;
    private String passphrase;

    public MainPresenter(MainActivity mainContext)
    {
        this.mainContext = mainContext;
    }

    public void setParams(EditText password)
    {
        //check decryption success
        this.passphrase = password.getText().toString();
        String PLACEHOLDER;
        if (true) {
            mainContext.showMessage("Decryption Successful");
        } else {
            mainContext.showMessage("Decryption Failed");
        }
    }

    @Override
    public void onItemClicked(View v)
    {
        if (v.getId() == R.id.unlock_button) {
            // do decryption

            Intent intent = new Intent(mainContext.getApplicationContext(), HomeView.class);
            intent.putExtra("name", "");
            intent.putExtra("password", passphrase);
            mainContext.startActivity(intent);
        }
        //add password reset logic here
    }

    @Override
    public Context getMainContext()
    {
        return null;
    }
}
