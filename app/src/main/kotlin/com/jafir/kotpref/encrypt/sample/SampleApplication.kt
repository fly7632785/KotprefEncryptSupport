package com.jafir.kotpref.encrypt.sample

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.google.gson.Gson
import com.jafir.kotpref.encrypt.support.SharedPrefCipherAdapter
import com.jafir.kotpref.encrypt.support.cipherAdapter
import com.jafir.kotpref.encrypt.support.gson


class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // If you don't use kotpref auto initializer, you should init kotpref.
        // Kotpref.init(this)

        // For gson support module
        Kotpref.gson = Gson()
        Kotpref.cipherAdapter = SharedPrefCipherAdapter(applicationContext)
    }
}
