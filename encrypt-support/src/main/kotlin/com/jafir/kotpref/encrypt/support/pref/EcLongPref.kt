package com.jafir.kotpref.encrypt.support.pref

import android.content.SharedPreferences
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.pref.AbstractPref
import com.jafir.kotpref.encrypt.support.cipherAdapter
import kotlin.reflect.KProperty


 class EcLongPref(val default: Long, val key: String?) : AbstractPref<Long>() {

    override fun getFromPreference(property: KProperty<*>, preference: SharedPreferences): Long {
        if (Kotpref.cipherAdapter != null) {
            try {
                return Kotpref.cipherAdapter!!.decrypt(preference.getString(key ?: property.name, default.toString())).toLong()
            } catch (e: Exception) {
                return default
            }
        }
        return default
    }

    override fun setToPreference(property: KProperty<*>, value: Long, preference: SharedPreferences) {
        if (Kotpref.cipherAdapter != null) {
            try {
                preference.edit().putString(key ?: property.name, Kotpref.cipherAdapter!!.encrypt(value.toString())).apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun setToEditor(property: KProperty<*>, value: Long, editor: SharedPreferences.Editor) {
        if (Kotpref.cipherAdapter != null) {
            try {
                editor.putString(key ?: property.name, Kotpref.cipherAdapter!!.encrypt(value.toString()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
