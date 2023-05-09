package com.example.fakestoreapi.model.localdata

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil(private val applicationContext: Context) {
    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences =
            applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    var userId: Int?
        get() = sharedPreferences?.getInt(KEY_USERID, NOT_LOGIN)
        set(value) {
            sharedPreferences?.edit()?.putInt(KEY_USERID, value ?: NOT_LOGIN)?.apply()
        }

    fun deleteLoginData() {
        userId = NOT_LOGIN
    }

    fun isLoggedIn(): Boolean {
        return userId != NOT_LOGIN
    }

    companion object {
        private const val NOT_LOGIN = -1
        private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"
        private const val KEY_USERID = "keyUserId"
    }
}