package com.iso.gallery256.Presenter;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


//import com.iso.gallery256.Model.database.threading.DatabaseDeleteRunnable;
import com.iso.gallery256.Model.database.threading.DatabaseDeletionRunnable;
import com.iso.gallery256.Model.database.threading.DatabaseQueryRunnable;
import com.iso.gallery256.Activity.HomeView;

import java.net.URI;
import java.util.ArrayList;

import com.iso.gallery256.Model.Photo;
import com.iso.gallery256.Model.database.AlbumDatabase;
import com.iso.gallery256.Model.database.PhotoDatabase;
import com.iso.gallery256.R;
import com.iso.gallery256.Utils.FileHandler;
import com.iso.gallery256.adapters.PhotoAdapter;

public class FragmentPresenter {

    private AlbumDatabase albumDatabase; //must inherent from base  database
    private PhotoDatabase photoDatabase;
    private HandlerThread handlerThread;
    private PhotoAdapter adapter;
    private Context context;

    public FragmentPresenter(Fragment mainFragment, PhotoAdapter adapter)
    {
        this.handlerThread = new HandlerThread("Database");
        handlerThread.start();
        this.albumDatabase = new AlbumDatabase(mainFragment.getContext());
        this.photoDatabase = new PhotoDatabase(mainFragment.getContext());
        this.adapter = adapter;
        this.context = mainFragment.getContext();
    }

    public void getPhotos(Context context, String name, ArrayList<Photo> photos)
    {
        Handler handler = new Handler(handlerThread.getLooper());

        if (context instanceof HomeView){
            handler.post(new DatabaseQueryRunnable(albumDatabase, adapter, photos, null));

        } else {
            handler.post(new DatabaseQueryRunnable(photoDatabase, adapter, photos, name));
        }
    }

    public void deletePhotos()
    {
        ArrayList<Photo> deletionQ = adapter.getDeleteQueue();
        for (Photo photo : deletionQ) {
            deletePhotos(photo.getName(), context.getContentResolver());
        }
    }

    public void deletePhotos(String albumName, ContentResolver contentResolver)
    {
        HandlerThread handlerThread = new HandlerThread("Database_delete");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        LinearLayout ll = (LinearLayout) ((Activity)context).findViewById(R.id.llProgressBar);
        TextView progressText = ll.findViewById(R.id.pbText);
        progressText.setVisibility(View.INVISIBLE);
        progressText.setText("Deleting album...");
        handler.post(new DatabaseDeletionRunnable(this, null, contentResolver, albumName, progressText, ll, 0, 0));
    }

    public void photoDeleter(String albumName, ContentResolver contentresolver) {
        FileHandler fileHandler = new FileHandler(context);
        ArrayList<Photo> tmp;
        tmp = albumDatabase.deleteAlbum(albumName);
        fileHandler.deleteFile(URI.create(tmp.get(0).getPhotoLocation()));
        tmp = photoDatabase.deletePhotosbyName(albumName);
        for (Photo photo : tmp) {
            fileHandler.deleteFile(URI.create(photo.getPhotoLocation()));
        }
    }
}
