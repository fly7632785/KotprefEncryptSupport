package com.jafir.kotpref.encrypt.support.pref

import android.content.SharedPreferences
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.pref.AbstractPref
import com.jafir.kotpref.encrypt.support.gson
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


class GsonPref<T : Any>(val targetClass: KClass<T>, val default: T, val key: String?) : AbstractPref<T>() {

    override fun getFromPreference(property: KProperty<*>, preference: SharedPreferences): T {
        return preference.getString(key ?: property.name, null)?.let { json ->
            deserializeFromJson(json) ?: default
        } ?: default
    }

    override fun setToPreference(property: KProperty<*>, value: T, preference: SharedPreferences) {
        serializeToJson(value).let { json ->
            preference.edit().putString(key ?: property.name, json).apply()
        }
    }

    override fun setToEditor(property: KProperty<*>, value: T, editor: SharedPreferences.Editor) {
        serializeToJson(value).let { json ->
            editor.putString(key ?: property.name, json)
        }
    }

    private fun serializeToJson(value: T?): String? {
        return Kotpref.gson.let {
            if (it == null) throw IllegalStateException("Gson has not been set to Kotpref")

            it.toJson(value)
        }
    }

    private fun deserializeFromJson(json: String): T? {
        return Kotpref.gson.let {
            if (it == null) throw IllegalStateException("Gson has not been set to Kotpref")

            it.fromJson(json, targetClass.java)
        }
    }
}
