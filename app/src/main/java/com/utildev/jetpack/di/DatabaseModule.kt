package com.utildev.jetpack.di

import android.content.Context
import androidx.room.Room
import com.utildev.jetpack.data.local.persistence.AppDao
import com.utildev.jetpack.data.local.persistence.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()

    @Provides
    fun provideDBDao(database: AppDatabase): AppDao = database.appDao()
}