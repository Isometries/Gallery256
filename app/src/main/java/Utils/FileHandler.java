package Utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.UUID;

import Crypto.EncryptionHelper;

public class FileHandler {

//    private Context appContext;
    private File rootDir, photoDir;
    EncryptionHelper cryptoStream;

    public FileHandler(Context appContext)
    {
//        this.appContext = appContext;
        //set and check directories
        this.rootDir = new File(appContext.getFilesDir(),"root");

        if(!rootDir.exists()){
            rootDir.mkdir();
        }
        this.photoDir = new File(rootDir, "photos");

        if (!photoDir.exists()) {
            photoDir.mkdir();
        }
        cryptoStream = new EncryptionHelper(appContext);

    }

    public File createFile(String fileName)
    {
        return new File(rootDir, fileName);
    }

    public File encryptFile(File file)
    {
        //check for collisions later
        //must be at least 16 bytes long
        String fileName = UUID.randomUUID().toString();
        File newFile = new File(photoDir, fileName);
        try {

            byte[] plaintext = Conversions.getBytesfromFile(file);
            byte[] ciphertext = cryptoStream.encryptByteArray(plaintext, fileName);

            FileOutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(ciphertext);

            return newFile;
        } catch (IOException | InvalidKeyException e) {
            Log.d("Failure to encrypt file", "fail");
            e.printStackTrace();
        }
        return null;
    }

}
