package com.utildev.jetpack.data.local.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.utildev.jetpack.data.local.persistence.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}