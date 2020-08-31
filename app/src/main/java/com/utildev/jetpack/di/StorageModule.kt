package com.utildev.jetpack.di

import com.utildev.jetpack.data.local.storage.SharedPreferencesStorage
import com.utildev.jetpack.data.local.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(sharedPrefStorage: SharedPreferencesStorage): Storage
}