package Model.database;

import android.content.Context;

public class PhotoDatabase extends BaseDatabase {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "photo.db";

    public PhotoDatabase(Context context)
    {
        super(context, DB_NAME, DB_VERSION);
    }

}
