package com.iso.gallery256.View.presenters;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class PhotoPresenter implements PresenterBase {

     /* TO DO
        flesh-out all model classes
     */

    private Activity mainContext;

    public PhotoPresenter(Activity mainContext)
    {
        this.mainContext = mainContext;
    }

    public void populateFragment()
    {
        //decrypt photos and populate the fragment
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onItemClicked(View v)
    {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Context getMainContext() {
        return null;
    }
}
