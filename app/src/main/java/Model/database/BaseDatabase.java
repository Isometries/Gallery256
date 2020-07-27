package Model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import Model.Photo;

public abstract class BaseDatabase extends SQLiteOpenHelper {

    public BaseDatabase(Context context, String databaseName, int databaseVersion)
    {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(DatabaseContract.DataTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL(DatabaseContract.DataTable.DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Photo> getPhotos()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Photo> photoList = new ArrayList<>();
        String query = "SELECT name, photo_location, thumbnail, album_id FROM " + DatabaseContract.DataTable.TABLE_DESCRIPTION;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Photo entry = new Photo(
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.DataTable.KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.DataTable.KEY_PHOTO_LOCATION)),
                    cursor.getBlob(cursor.getColumnIndex(DatabaseContract.DataTable.KEY_THUMBNAIL)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseContract.DataTable.KEY_ALBUM_ID))
            );
            photoList.add(entry);
        }
        db.close();
        return photoList;
    }

    public void addPhoto(String name, String photoLocation, byte[] thumbnail, int albumID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(DatabaseContract.DataTable.KEY_NAME, name);
        cValues.put(DatabaseContract.DataTable.KEY_PHOTO_LOCATION, photoLocation);
        cValues.put(DatabaseContract.DataTable.KEY_THUMBNAIL, thumbnail);
        cValues.put(DatabaseContract.DataTable.KEY_ALBUM_ID, albumID);

        db.insert(DatabaseContract.DataTable.TABLE_DESCRIPTION, null, cValues);
        Log.i("Photo added: ", " " + name);
        db.close();
    }

    public ArrayList<Photo> getPhotos(String albumName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Photo> photoList = new ArrayList<>();
        String query = "SELECT name, photo_location, thumbnail, album_id FROM "
                + DatabaseContract.DataTable.TABLE_DESCRIPTION
                + " WHERE " + DatabaseContract.DataTable.KEY_NAME + "=?";
        String[] searchParam = new String[] {albumName};
        Cursor cursor = db.rawQuery(query, searchParam);

        while (cursor.moveToNext()) {
            Photo entry = new Photo(
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.DataTable.KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.DataTable.KEY_PHOTO_LOCATION)),
                    cursor.getBlob(cursor.getColumnIndex(DatabaseContract.DataTable.KEY_THUMBNAIL)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseContract.DataTable.KEY_ALBUM_ID))
            );
            photoList.add(entry);
        }
        Log.i("FOUND", " "+albumName);
        db.close();
        return photoList;
    }

    public long getSize()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, DatabaseContract.DataTable.TABLE_DESCRIPTION);
    }
}
