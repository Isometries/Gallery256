package com.iso.gallery256.Model;

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

    public String getName()
    {
        return name;
    }

    public String getPhotoLocation()
    {
        return photoLocation;
    }

    public byte[] getThumbNail()
    {
        return thumbnail;
    }

}
