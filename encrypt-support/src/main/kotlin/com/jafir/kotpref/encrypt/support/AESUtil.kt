package com.jafir.kotpref.encrypt.support

import android.content.Context
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.Security
import java.security.spec.InvalidKeySpecException
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec

/**
 * Created by yume on 17-03-21.
 */
object AESUtil {
    private val IV = byteArrayOf(16, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74)
    private val SALT: ByteArray = byteArrayOf(-47, 66, 32, -127, -102, -51, 79, -69, 57, 85, -91, -42, 74, -116, -46, -113, -13, 34, -24, 39)
    private val TAG = AESUtil::class.java.simpleName

    @Throws(NoSuchAlgorithmException::class, NoSuchPaddingException::class, InvalidKeyException::class, InvalidAlgorithmParameterException::class, IllegalBlockSizeException::class, BadPaddingException::class)
    fun decrypt(paramArrayOfByte: ByteArray, secretKey: SecretKey): ByteArray {
        val localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        localCipher.init(2, secretKey, IvParameterSpec(IV))
        return localCipher.doFinal(paramArrayOfByte)
    }

    @Throws(NoSuchAlgorithmException::class, NoSuchPaddingException::class, InvalidKeyException::class, InvalidAlgorithmParameterException::class, IllegalBlockSizeException::class, BadPaddingException::class)
    fun encrypt(paramArrayOfByte: ByteArray, secretKey: SecretKey): ByteArray {
        val localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        localCipher.init(1, secretKey, IvParameterSpec(IV))
        return localCipher.doFinal(paramArrayOfByte)
    }

    fun execDecrypted(secretKey: SecretKey, paramString: String): String? {
        try {
            return String(decrypt(Base64.decode(paramString, Base64.DEFAULT), secretKey))
        } catch (e: Exception) {
            Log.e(TAG, "execDecrypted", e)
        }

        return null
    }

    fun execEncrypted(secretKey: SecretKey, paramString: String): String? {
        try {
            return Base64.encodeToString(encrypt(paramString.toByteArray(), secretKey), Base64.DEFAULT)
        } catch (e: Exception) {
            Log.e(TAG, "execEncrypted", e)
        }

        return null
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class, PackageManager.NameNotFoundException::class)
    fun generateKey(paramContext: Context): SecretKey {
        val pbeKeySpec = PBEKeySpec(generatePassword(paramContext), SALT, 1024, 256)
        return SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC").generateSecret(pbeKeySpec)
    }

    @Throws(PackageManager.NameNotFoundException::class)
    fun generatePassword(paramContext: Context): CharArray {
        val l = paramContext.packageManager
                .getPackageInfo(paramContext.packageName, PackageManager.GET_META_DATA)
                .firstInstallTime
        val packageName = paramContext.packageName
        return (l.toString() + packageName).toCharArray()
    }

    fun printAlgorithmsCipher() {
        val localIterator = Security.getAlgorithms("Cipher").iterator()
        while (localIterator.hasNext()) {
            val str = localIterator.next() as String
            Log.d("Cipher" + ": ", str)
        }
    }
}