package com.jafir.kotpref.encrypt.support.pref

import android.content.SharedPreferences
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.pref.AbstractPref
import com.jafir.kotpref.encrypt.support.cipherAdapter
import kotlin.reflect.KProperty


 class EcBooleanPref(val default: Boolean, val key: String?) : AbstractPref<Boolean>() {
    override fun getFromPreference(property: KProperty<*>, preference: SharedPreferences): Boolean {
        if (Kotpref.cipherAdapter != null) {
            try {
                return Kotpref.cipherAdapter!!.decrypt(preference.getString(key ?: property.name, default.toString())).toBoolean()
            } catch (e: Exception) {
                return default
            }
        }
        return default
    }

    override fun setToPreference(property: KProperty<*>, value: Boolean, preference: SharedPreferences) {
        if (Kotpref.cipherAdapter != null) {
            try {
                preference.edit().putString(key ?: property.name, Kotpref.cipherAdapter!!.encrypt(value.toString())).apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun setToEditor(property: KProperty<*>, value: Boolean, editor: SharedPreferences.Editor) {
        if (Kotpref.cipherAdapter != null) {
            editor.putString(key ?: property.name, Kotpref.cipherAdapter!!.encrypt(value.toString()))
            try {
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
