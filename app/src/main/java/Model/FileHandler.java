package Model;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileHandler {

    private Context appContext;
    private File rootDir, photoDir;

    public FileHandler(Context appContext)
    {
        this.appContext = appContext;
        this.rootDir = new File(appContext.getFilesDir(),"root");

        if(!rootDir.exists()){
            rootDir.mkdir();
        }
        this.photoDir = new File(rootDir, "photos");
    }

    public File createFile(String fileName)
    {
        return new File(rootDir, fileName);
    }

    //add encryption later
//    public Uri savePhoto(byte[] photoBytes, String name)
//    {
//        File newFile = new File(photoDir, name + ".dat");
//        try {
//            FileOutputStream outputStream = new FileOutputStream(newFile);
//            outputStream.write(photoBytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return
//    }
}
