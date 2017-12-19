package com.jafir.kotpref.encrypt.support

import android.content.Context
import javax.crypto.SecretKey

/**
 * Created by jafir on 2017/12/19.
 */
class SharedPrefCipherAdapter @Throws(Exception::class)
constructor(context: Context) : CipherAdapter {
    private val secretKey: SecretKey = AESUtil.generateKey(context)

    override fun encrypt(encode: String): String {
        return AESUtil.execEncrypted(secretKey, encode)
    }

    override fun decrypt(decode: String): String {
        return AESUtil.execDecrypted(secretKey, decode)
    }
}