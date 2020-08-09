package com.utildev.jetpack.data.local.sharedprefs

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefs @Inject constructor(private val sharedPrefs: SharedPreferences) {
    fun putString(key: String, value: String?) {
        sharedPrefs.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? = sharedPrefs.getString(key, "")
}