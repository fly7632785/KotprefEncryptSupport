package com.jafir.kotpref.encrypt.sample

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.enumpref.enumValuePref
import com.jafir.kotpref.encrypt.support.*
import java.util.*


object UserInfo : KotprefModel() {
    var gameLevel by enumValuePref(GameLevel.NORMAL)
    var name by stringPref("mememe")
    var code by nullableStringPref()
    var age by intPref()
    var highScore by longPref()
    var rate by floatPref()
    val prizes by stringSetPref {
        val set = TreeSet<String>()
        set.add("Beginner")
        return@stringSetPref set
    }
    var avatar by gsonPref(Avatar())
    var avatar1 by gsonNullablePref(Avatar())


    var password by ecStringPref("jafirPass")
    var code1 by ecNullableStringPref()
    var isMan by ecBooleanPref(true)
    var age1 by ecIntPref(23)
    var highScore1 by ecLongPref(1111111111L)
    var rate1 by ecFloatPref(0.5555f)
    var person1 by ecGsonPref(Person("g jafir", 21))
    var avatar21 by ecGsonPref(Avatar())
    var avatar22 by ecGsonNullablePref(Avatar())


    data class Person(val name: String, val age: Int)
}
