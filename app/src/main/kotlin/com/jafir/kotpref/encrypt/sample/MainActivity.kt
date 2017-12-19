package com.jafir.kotpref.encrypt.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.chibatching.kotpref.bulk
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printUser()
        Log.d(TAG, "\n\nchange value")

        UserInfo.gameLevel = GameLevel.HARD
        UserInfo.name = "chibatching"
        UserInfo.code = "DAEF2599-7FC9-49C5-9A11-3C12B14A6898"
        UserInfo.age = 30
        UserInfo.highScore = 49219902
        UserInfo.rate = 49.21F
        UserInfo.prizes.add("Bronze")
        UserInfo.prizes.add("Silver")
        UserInfo.prizes.add("Gold")
        UserInfo.avatar = Avatar("bird", Date())
        UserInfo.avatar1 = null

        UserInfo.password = "newpass12312321...324"
        UserInfo.code1 = "DAEF2599-7FC9-49C5-9A11-3C12B14A6898"
        UserInfo.age1 = 30
        UserInfo.isMan = false
        UserInfo.highScore1 = 49219902
        UserInfo.rate1 = 49.21F
        UserInfo.person1 = UserInfo.Person("2 jafir", 110)
        UserInfo.avatar21 = Avatar("encyption bird", Date())
        UserInfo.avatar22 = null

        printUser()
        Log.d(TAG, "\n\n change value by bulk")

        UserInfo.bulk {
            gameLevel = GameLevel.EASY
            name = "chibatching Jr"
            code = "451B65F6-EF95-4C2C-AE76-D34535F51B3B"
            age = 2
            highScore = 3901
            rate = 0.4F
            prizes.clear()
            prizes.add("New Born")
            avatar = Avatar("lion", Date())
            isMan =true
            password = "bulkPass123213"
            person1 = UserInfo.Person("gg jafir", 120)
            avatar21 = Avatar("bulk lion", Date())
            avatar22 = Avatar("bulk lion2", Date())
        }

        printUser()

    }

    private fun printUser() {
        Log.d(TAG, "Game level: ${UserInfo.gameLevel}")
        Log.d(TAG, "User name: ${UserInfo.name}")
        Log.d(TAG, "User code: ${UserInfo.code}")
        Log.d(TAG, "User age: ${UserInfo.age}")
        Log.d(TAG, "User high score: ${UserInfo.highScore}")
        Log.d(TAG, "User rate: ${UserInfo.rate}")
        Log.d(TAG, "Avatar: ${UserInfo.avatar}")
        Log.d(TAG, "Avatar1: ${UserInfo.avatar1}")
        UserInfo.prizes.forEachIndexed { i, s -> Log.d(TAG, "prize[$i]: $s") }

        Log.d(TAG, "encrypted Pass: ${UserInfo.password}")
        Log.d(TAG, "encrypted User isMan: ${UserInfo.isMan}")
        Log.d(TAG, "encrypted User code1: ${UserInfo.code1}")
        Log.d(TAG, "encrypted User age1: ${UserInfo.age1}")
        Log.d(TAG, "encrypted User high score1: ${UserInfo.highScore1}")
        Log.d(TAG, "encrypted User rate1: ${UserInfo.rate1}")
        Log.d(TAG, "encrypted Person: ${UserInfo.person1}")
        Log.d(TAG, "encrypted Avatar21: ${UserInfo.avatar21}")
        Log.d(TAG, "encrypted Avatar22: ${UserInfo.avatar22}")
    }
}
