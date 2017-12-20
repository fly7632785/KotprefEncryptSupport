package com.jafir.kotpref.encrypt.support

import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.KotprefModel
import com.google.gson.Gson
import com.jafir.kotpref.encrypt.support.pref.*
import kotlin.properties.ReadWriteProperty


/**
 * Gson object to serialize and deserialize delegated property
 */
var Kotpref.cipherAdapter: CipherAdapter?
    get() {
        return KotprefCipherHolder.cipherAdapter
    }
    set(value) {
        KotprefCipherHolder.cipherAdapter = value
    }
/**
 * Delegate string shared preferences property.
 * @param default default string value
 * @param key custom preferences key
 */
inline fun KotprefModel.ecStringPref(default: String = "", key: String? = null)
        : ReadWriteProperty<KotprefModel, String> = EcStringPref(default, key)


/**
 * Delegate string shared preferences property.
 * @param default default string value
 * @param key custom preferences key resource id
 */
inline fun KotprefModel.ecStringPref(default: String = "", key: Int)
        : ReadWriteProperty<KotprefModel, String> = ecStringPref(default, context.getString(key))

/**
 * Delegate nullable string shared preferences property.
 * @param default default string value
 * @param key custom preferences key
 */
inline fun KotprefModel.ecNullableStringPref(default: String? = null, key: String? = null)
        : ReadWriteProperty<KotprefModel, String?> = EcStringNullablePref(default, key)

/**
 * Delegate nullable string shared preferences property.
 * @param default default string value
 * @param key custom preferences key resource id
 */
inline fun KotprefModel.ecNullableStringPref(default: String? = null, key: Int)
        : ReadWriteProperty<KotprefModel, String?> = EcStringNullablePref(default, context.getString(key))

/**
 * Delegate int shared preferences property.
 * @param default default int value
 * @param key custom preferences key
 */
inline fun KotprefModel.ecIntPref(default: Int = 0, key: String? = null)
        : ReadWriteProperty<KotprefModel, Int> = EcIntPref(default, key)

/**
 * Delegate int shared preferences property.
 * @param default default int value
 * @param key custom preferences key resource id
 */
inline fun KotprefModel.ecIntPref(default: Int = 0, key: Int)
        : ReadWriteProperty<KotprefModel, Int> = ecIntPref(default, context.getString(key))

/**
 * Delegate long shared preferences property.
 * @param default default long value
 * @param key custom preferences key
 */
inline fun KotprefModel.ecLongPref(default: Long = 0L, key: String? = null)
        : ReadWriteProperty<KotprefModel, Long> = EcLongPref(default, key)

/**
 * Delegate long shared preferences property.
 * @param default default long value
 * @param key custom preferences key resource id
 */
inline fun KotprefModel.ecLongPref(default: Long = 0L, key: Int)
        : ReadWriteProperty<KotprefModel, Long> = ecLongPref(default, context.getString(key))

/**
 * Delegate float shared preferences property.
 * @param default default float value
 * @param key custom preferences key
 */
inline fun KotprefModel.ecFloatPref(default: Float = 0F, key: String? = null)
        : ReadWriteProperty<KotprefModel, Float> = EcFloatPref(default, key)

/**
 * Delegate float shared preferences property.
 * @param default default float value
 * @param key custom preferences key resource id
 */
inline fun KotprefModel.ecFloatPref(default: Float = 0F, key: Int)
        : ReadWriteProperty<KotprefModel, Float> = ecFloatPref(default, context.getString(key))

/**
 * Delegate boolean shared preferences property.
 * @param default default boolean value
 * @param key custom preferences key
 */
inline fun KotprefModel.ecBooleanPref(default: Boolean = false, key: String? = null)
        : ReadWriteProperty<KotprefModel, Boolean> = EcBooleanPref(default, key)

/**
 * Delegate boolean shared preferences property.
 * @param default default boolean value
 * @param key custom preferences key resource id
 */
inline fun KotprefModel.ecBooleanPref(default: Boolean = false, key: Int)
        : ReadWriteProperty<KotprefModel, Boolean> = ecBooleanPref(default, context.getString(key))


/**
 * #################  Include gson support from   compile "com.chibatching.kotpref:gson-support:2.2.0"  ################
 */


/**
 * Gson object to serialize and deserialize delegated property
 */

var Kotpref.gson: Gson?
    get() {
        return KotprefGsonHolder.gson
    }
    set(value) {
        KotprefGsonHolder.gson = value
    }

/**
 * Delegate shared preferences property serialized and deserialized by gson
 * @param default default gson object value
 * @param key custom preferences key
 */
inline fun <reified T : Any> KotprefModel.gsonPref(default: T, key: String? = null)
        : ReadWriteProperty<KotprefModel, T> = GsonPref(T::class, default, key)

/**
 * Delegate shared preferences property serialized and deserialized by gson
 * @param default default gson object value
 * @param key custom preferences key resource id
 */
inline fun <reified T : Any> KotprefModel.gsonPref(default: T, key: Int)
        : ReadWriteProperty<KotprefModel, T> = GsonPref(T::class, default, context.getString(key))

/**
 * Delegate shared preferences property serialized and deserialized by gson
 * @param default default gson object value
 * @param key custom preferences key
 */
inline fun <reified T : Any> KotprefModel.gsonNullablePref(default: T? = null, key: String? = null)
        : ReadWriteProperty<KotprefModel, T?> = GsonNullablePref(T::class, default, key)

/**
 * Delegate shared preferences property serialized and deserialized by gson
 * @param default default gson object value
 * @param key custom preferences key resource id
 */
inline fun <reified T : Any> KotprefModel.gsonNullablePref(default: T? = null, key: Int)
        : ReadWriteProperty<KotprefModel, T?> = GsonNullablePref(T::class, default, context.getString(key))

/**
 * Delegate shared preferences property serialized and deserialized by gson
 * @param default default gson object value
 * @param key custom preferences key
 */
inline fun <reified T : Any> KotprefModel.ecGsonPref(default: T, key: String? = null)
        : ReadWriteProperty<KotprefModel, T> = EcGsonPref(T::class, default, key)

/**
 * Delegate shared preferences property serialized and deserialized by gson
 * @param default default gson object value
 * @param key custom preferences key resource id
 */
inline fun <reified T : Any> KotprefModel.ecGsonPref(default: T, key: Int)
        : ReadWriteProperty<KotprefModel, T> = EcGsonPref(T::class, default, context.getString(key))

/**
 * Delegate shared preferences property serialized and deserialized by gson
 * @param default default gson object value
 * @param key custom preferences key
 */
inline fun <reified T : Any> KotprefModel.ecGsonNullablePref(default: T? = null, key: String? = null)
        : ReadWriteProperty<KotprefModel, T?> = EcGsonNullablePref(T::class, default, key)

/**
 * Delegate shared preferences property serialized and deserialized by gson
 * @param default default gson object value
 * @param key custom preferences key resource id
 */
inline fun <reified T : Any> KotprefModel.ecGsonNullablePref(default: T? = null, key: Int)
        : ReadWriteProperty<KotprefModel, T?> = EcGsonNullablePref(T::class, default, context.getString(key))