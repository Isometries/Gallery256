package Crypto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;

import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import static java.lang.System.exit;


public class EncryptionHelper {

    private String passphrase;
    private byte[] iv;
    private SecretKey derivedKey;
    private final static int BLOCK_SIZE = 256;
    private final static int IV_SIZE = 16;
    private final static int ITERATION_COUNT = 10;
    private final static String ENCRYPTION_ALGORITHM = "PBKDF2WithHmacSHA256";
    private final static String ALGORITHM = "AES/CBC/PKCS5Padding";

    public EncryptionHelper(Context context)
    {
        Intent intent = ((Activity) context).getIntent();
        this.passphrase = intent.getStringExtra("password");
    }

    private void genKey()
    {
        Log.i("Password intent", passphrase);
        if (iv.length == 0) {
            exit(1);
        }
        try {
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), iv, ITERATION_COUNT, BLOCK_SIZE);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ENCRYPTION_ALGORITHM);
            derivedKey = new SecretKeySpec(secretKeyFactory.generateSecret(spec).getEncoded(), "AES_256");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private void setIV(byte[] iv) throws InvalidKeyException {
        if (iv.length != IV_SIZE) {
            throw new InvalidKeyException();
        }
        this.iv = iv;
    }

    public byte[] encryptByteArray(byte[] plaintext, String fileName) throws InvalidKeyException
    {
        extractIVfromName(fileName);
        genKey();
        //failure in here
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec parameterSpec = new IvParameterSpec(iv);

            Log.d("encryptByteArray", "Size: " + plaintext.length + " IV SPEC: " +
                    Base64.encodeToString(parameterSpec.getIV(), Base64.DEFAULT) + "DerivedKey: " +
                    Base64.encodeToString(derivedKey.getEncoded(), Base64.DEFAULT));

            cipher.init(Cipher.ENCRYPT_MODE, derivedKey, parameterSpec);
            return cipher.doFinal(plaintext);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] decryptByteArray(byte[] ciphertext, String fileName) throws InvalidKeyException
    {
        extractIVfromName(fileName);
        genKey();
        try {
            //IV fails for some reason
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec parameterSpec = new IvParameterSpec(iv);
            Log.d("decryptByteArray", "Size: " + ciphertext.length + " IV SPEC: " +
                    Base64.encodeToString(parameterSpec.getIV(), Base64.DEFAULT) + "DerivedKey: " +
                    Base64.encodeToString(derivedKey.getEncoded(), Base64.DEFAULT));
            cipher.init(Cipher.DECRYPT_MODE, derivedKey, parameterSpec);
            return cipher.doFinal(ciphertext);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void extractIVfromName(String fileName) throws InvalidKeyException
    {
        byte[] tm = fileName.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(tm);
        byte[] newIV = new byte[IV_SIZE];
        byteBuffer.get(newIV, 0, IV_SIZE);

        setIV(newIV);
    }
}

