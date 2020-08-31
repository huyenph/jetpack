package com.utildev.jetpack.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.utildev.jetpack.BuildConfig
import com.utildev.jetpack.data.local.storage.SharedPreferencesStorage
import com.utildev.jetpack.data.remote.ApiService
import com.utildev.jetpack.data.remote.adapter.NetworkResponseAdapterFactory
import com.utildev.jetpack.data.remote.helper.HttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

const val CONNECT_TIMEOUT = 1L
const val READ_TIMEOUT = 1L
const val WRITE_TIMEOUT = 1L

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GsonBuilderLenient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthNetworkService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherNetworkService

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @GsonBuilderLenient
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(HttpInterceptor())
        .addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .build()
            chain.proceed(request)
        }
        .build()

    @OtherInterceptorOkHttpClient
    @Provides
    fun provideOtherInterceptorOkHttpClient(
        sharedPrefStorage: SharedPreferencesStorage
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(HttpInterceptor())
        .addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .addHeader("Authorization", "Bearer ${sharedPrefStorage.getString("token")}")
                .build()
            chain.proceed(request)
        }
        .build()

    @AuthNetworkService
    @Provides
    fun provideAuthNetworkService(
        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient,
        @GsonBuilderLenient gson: Gson
    ): ApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)

    @OtherNetworkService
    @Provides
    fun provideNetworkService(
        @OtherInterceptorOkHttpClient okHttpClient: OkHttpClient,
        @GsonBuilderLenient gson: Gson
    ): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)
}