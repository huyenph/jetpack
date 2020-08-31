package com.utildev.jetpack.data.local.storage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor(@ApplicationContext context: Context) : Storage {
    private val sharedPrefs = context.getSharedPreferences("Storage", Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) =
        with(sharedPrefs.edit()) {
            putString(key, value)
            apply()
        }

    override fun getString(key: String): String = sharedPrefs.getString(key, "")!!

    override fun setInt(key: String, value: Int) =
        with(sharedPrefs.edit()) {
            putInt(key, value)
            apply()
        }

    override fun getInt(key: String): Int = sharedPrefs.getInt(key, 0)

    override fun setFloat(key: String, value: Float) =
        with(sharedPrefs.edit()) {
            putFloat(key, value)
            apply()
        }

    override fun getFloat(key: String): Float = sharedPrefs.getFloat(key, 0F)

    override fun setLong(key: String, value: Long) =
        with(sharedPrefs.edit()) {
            putLong(key, value)
            apply()
        }

    override fun getLong(key: String): Long = sharedPrefs.getLong(key, 0L)

    override fun setBoolean(key: String, value: Boolean) =
        with(sharedPrefs.edit()) {
            putBoolean(key, value)
            apply()
        }

    override fun getBoolean(key: String): Boolean = sharedPrefs.getBoolean(key, false)

}