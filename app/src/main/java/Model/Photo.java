package Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Photo {

    /** TODO:
        fill in place holder intance vars
    */

    String photoLocation, name;
    byte[] thumbnail;
    int albumID;



    public Photo(String name, String photoLocation, byte[] thumbNail,  int albumID)
    {
        this.photoLocation = photoLocation;
        this.thumbnail = thumbNail;
        this.albumID = albumID;
        this.name = name;
    }


    public byte[] getThumbNail()
    {
        return thumbnail;
    }

}
