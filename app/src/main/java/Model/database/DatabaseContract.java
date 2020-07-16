package Model.database;

import android.provider.BaseColumns;


public class DatabaseContract {

    public DatabaseContract() {}

    public static abstract class DataTable implements BaseColumns {
        public static final String TABLE_DESCRIPTION = "details";
        public static final String KEY_NAME = "name";
        public static final String KEY_ID = "id";
        public static final String KEY_PHOTO_LOCATION = "photo_location";
        public static final String KEY_THUMBNAIL = "thumbnail";
        public static final String KEY_ALBUM_ID = "album_id";

        public static String CREATE_TABLE = "CREATE TABLE " + TABLE_DESCRIPTION + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_PHOTO_LOCATION + " TEXT,"
                + KEY_THUMBNAIL + " BLOB,"
                + KEY_ALBUM_ID + " INTEGER" + ")";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_DESCRIPTION;
    }

}
