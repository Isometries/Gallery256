package com.iso.gallery256.Presenter;

import android.content.Context;
import android.view.View;

public interface PresenterBase {

    /* TO DO
        flesh-out all model classes
     */

    void onResume();

    void onItemClicked(View v);

    void onDestroy();

    Context getMainContext();
}
