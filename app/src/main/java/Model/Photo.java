package Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Photo {

    /** TODO:
        fill in place holder intance vars
    */

    String imageLocation;
    Bitmap thumbNail;


    public Photo(String imageLocation, Bitmap thumbNail)
    {
        this.imageLocation = imageLocation;
        this.thumbNail = thumbNail;
    }

    //opposite of writeToParcel
    protected Photo(Parcel in)
    {

    }

    public Bitmap getThumbNail()
    {
        return thumbNail;
    }

}
