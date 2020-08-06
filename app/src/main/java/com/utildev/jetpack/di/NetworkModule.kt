package com.utildev.jetpack.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.utildev.jetpack.BuildConfig
import com.utildev.jetpack.data.remote.ApiService
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

const val CONNECT_TIMEOUT = 1L
const val READ_TIMEOUT = 1L
const val WRITE_TIMEOUT = 1L

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(HttpInterceptor())
        .build()

    @OtherInterceptorOkHttpClient
    @Provides
    fun provideOtherInterceptorOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(HttpInterceptor())
        .addInterceptor { chain ->
            val request: Request = chain.request().newBuilder().addHeader("", "").build()
            chain.proceed(request)
        }
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideAuthNetworkService(
        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient,
        gson: Gson
    ): ApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)

    @Provides
    fun provideNetworkService(
        @OtherInterceptorOkHttpClient okHttpClient: OkHttpClient,
        gson: Gson
    ): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)

}