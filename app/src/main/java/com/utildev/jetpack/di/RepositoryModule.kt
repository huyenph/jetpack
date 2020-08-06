package com.utildev.jetpack.di

import com.utildev.jetpack.data.repository.AuthRepositoryImpl
import com.utildev.jetpack.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun authApiService(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}