package com.utildev.jetpack.data.local.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.utildev.jetpack.data.local.persistence.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase =
//            instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app.db")
//                .addCallback(object : RoomDatabase.Callback() {
//                    override fun onCreate(db: SupportSQLiteDatabase) {
//                        super.onCreate(db)
//                    }
//                })
//                .build()
//    }
}