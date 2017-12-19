package com.jafir.kotpref.encrypt.support.pref

import android.content.SharedPreferences
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.pref.AbstractPref
import com.jafir.kotpref.encrypt.support.cipherAdapter
import kotlin.reflect.KProperty


 class EcStringNullablePref(val default: String?, val key: String?) : AbstractPref<String?>() {

    override fun getFromPreference(property: KProperty<*>, preference: SharedPreferences): String? {
        if (Kotpref.cipherAdapter != null) {
            try {
                return Kotpref.cipherAdapter!!.decrypt(preference.getString(key ?: property.name, default))
            } catch (e: Exception) {
                return default
            }
        }
        return default
    }

    override fun setToPreference(property: KProperty<*>, value: String?, preference: SharedPreferences) {
        if (Kotpref.cipherAdapter != null && value != null) {
            try {
                preference.edit().putString(key ?: property.name, Kotpref.cipherAdapter!!.encrypt(value)).apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun setToEditor(property: KProperty<*>, value: String?, editor: SharedPreferences.Editor) {
        if (Kotpref.cipherAdapter != null && value != null) {
            try {
                editor.putString(key ?: property.name, Kotpref.cipherAdapter!!.encrypt(value))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
