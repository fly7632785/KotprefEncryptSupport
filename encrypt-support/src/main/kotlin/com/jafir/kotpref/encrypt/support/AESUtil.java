package com.jafir.kotpref.encrypt.support;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.Log;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;

/**
 * Created by yume on 17-03-21.
 */
public class AESUtil {
    private static final byte[] IV = {16, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74};
    private static final byte[] SALT;
    private static final String TAG = AESUtil.class.getSimpleName();

    static {
        SALT = new byte[]{-47, 66, 32, -127, -102, -51, 79, -69, 57, 85, -91, -42, 74, -116, -46, -113, -13, 34, -24, 39};
    }

    public static byte[] decrypt(byte[] paramArrayOfByte, SecretKey secretKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        localCipher.init(2, secretKey, new IvParameterSpec(IV));
        return localCipher.doFinal(paramArrayOfByte);
    }

    public static byte[] encrypt(byte[] paramArrayOfByte, SecretKey secretKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        localCipher.init(1, secretKey, new IvParameterSpec(IV));
        return localCipher.doFinal(paramArrayOfByte);
    }

    public static String execDecrypted(SecretKey secretKey, String paramString) {
        try {
            return new String(decrypt(Base64.decode(paramString, Base64.DEFAULT), secretKey));
        } catch (Exception e) {
            Log.e(TAG, "execDecrypted", e);
        }
        return null;
    }

    public  static String execEncrypted(SecretKey secretKey, String paramString) {
        try {
            return Base64.encodeToString(encrypt(paramString.getBytes(), secretKey), Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(TAG, "execEncrypted", e);
        }
        return null;
    }

    public  static SecretKey generateKey(Context paramContext)
            throws NoSuchAlgorithmException, InvalidKeySpecException, PackageManager.NameNotFoundException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(generatePassword(paramContext), SALT, 1024, 256);
        return SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC").generateSecret(pbeKeySpec);
    }

    public static char[] generatePassword(Context paramContext)
            throws PackageManager.NameNotFoundException {
        long l = paramContext.getPackageManager()
                .getPackageInfo(paramContext.getPackageName(), PackageManager.GET_META_DATA)
                .firstInstallTime;
        String packageName = paramContext.getPackageName();
        return (String.valueOf(l) + packageName).toCharArray();
    }

    public static void printAlgorithmsCipher() {
        Iterator localIterator = Security.getAlgorithms("Cipher").iterator();
        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
            Log.d("Cipher" + ": ", str);
        }
    }
}