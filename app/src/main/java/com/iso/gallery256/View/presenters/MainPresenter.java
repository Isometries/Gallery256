package com.iso.gallery256.View.presenters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.iso.gallery256.R;
import com.iso.gallery256.View.activities.AlbumView;
import com.iso.gallery256.View.activities.MainActivity;

public class MainPresenter implements PresenterBase {

     /* TO DO
        flesh-out all model classes
     */

    private MainActivity mainContext;

    public MainPresenter(MainActivity mainContext)
    {
        this.mainContext = mainContext;
    }

    public void sendPassword(EditText password)
    {
        //check decryption succes
        String PLACEHOLDER;
        if (true) {
            mainContext.showMessage("Decryption Successful");
        } else {
            mainContext.showMessage("Decryption Failed");
        }
    }

    @Override
    public void onResume()
    {

    }

    @Override
    public void onItemClicked(View v)
    {
        if (v.getId() == R.id.unlock_button) {
            // do decryption

            Intent intent = new Intent(mainContext.getApplicationContext(), AlbumView.class);
            mainContext.startActivity(intent);
        }
        //add password reset logic here
    }

    @Override
    public void onDestroy()
    {

    }

    @Override
    public Context getMainContext()
    {
        return null;
    }
}
