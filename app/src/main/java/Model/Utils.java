package Model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class Utils {

    public static File getFilefromUri(Uri uri, ContentResolver contentResolver, Context context)
    {
        FileHandler fileHandler = new FileHandler(context);
        File tmpFile = fileHandler.createFile("tmpfile");

        try {
            InputStream inputStream = contentResolver.openInputStream(uri);
            OutputStream outputStream = new FileOutputStream(tmpFile);
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            Log.d("FAILED something else", "REEEEE");
            e.printStackTrace();
        }
        return tmpFile;
    }

    public static byte[] getBytesfromFile(File file)
    {
        //if photo size is larger than (int)... shit's broken
        byte[] byteArray = new byte[(int) file.length()];
        try {
            FileInputStream fileIn = new FileInputStream(file);
            int read = fileIn.read(byteArray);
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    //function could use improvement
    public static byte[] getThumbnailfromFile(byte[] bytes)
    {
        Bitmap tmpBMP = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        Bitmap newBMP = ThumbnailUtils.extractThumbnail(tmpBMP, 512, 256);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        newBMP.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        newBMP.recycle();
        tmpBMP.recycle();

        return byteArray;
    }

    public static byte[] BMPtoBytes(Bitmap bmp)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bmp.recycle();
        return byteArray;
    }

    public static Bitmap BytestoBMP(byte[] bytes)
    {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
