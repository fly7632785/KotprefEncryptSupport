package com.jafir.kotpref.encrypt.support.pref

import android.content.SharedPreferences
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.pref.AbstractPref
import com.jafir.kotpref.encrypt.support.cipherAdapter
import kotlin.reflect.KProperty


 class EcIntPref(val default: Int, val key: String?) : AbstractPref<Int>() {

    override fun getFromPreference(property: KProperty<*>, preference: SharedPreferences): Int {
        if (Kotpref.cipherAdapter != null) {
            try {
                return Kotpref.cipherAdapter!!.decrypt(preference.getString(key ?: property.name, default.toString())).toInt()
            } catch (e: Exception) {
                return default
            }
        }
        return default
    }

    override fun setToPreference(property: KProperty<*>, value: Int, preference: SharedPreferences) {
        if (Kotpref.cipherAdapter != null) {
            try {
                preference.edit().putString(key ?: property.name, Kotpref.cipherAdapter!!.encrypt(value.toString())).apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun setToEditor(property: KProperty<*>, value: Int, editor: SharedPreferences.Editor) {
        if (Kotpref.cipherAdapter != null) {
            try {
                editor.putString(key ?: property.name, Kotpref.cipherAdapter!!.encrypt(value.toString()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
