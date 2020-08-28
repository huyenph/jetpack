package com.utildev.jetpack.di

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.utildev.jetpack.BuildConfig
import com.utildev.jetpack.data.local.storage.SharedPreferencesStorage
import com.utildev.jetpack.data.remote.ApiService
import com.utildev.jetpack.data.remote.adapter.NetworkResponseAdapterFactory
import com.utildev.jetpack.data.remote.helper.HttpError
import com.utildev.jetpack.data.remote.helper.HttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
//        .addInterceptor(HttpInterceptor())
        .addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .build()
            val response = chain.proceed(request)
            if (response.code >= 400) {
                throw Exception(HttpError.getErrorString(response))
            }
            if (response.body != null) {
                val r = response.body!!.string()
                val resultObject = JSONObject()
                if (TextUtils.equals(r, "[]")) {
                    /**
                     * Generate a success response.
                     * HTTP/1.1 200 OK
                     * Content-type: application/json
                     * "$random_string"
                     */
                    resultObject.put("message", "unknown result")
                    response.newBuilder()
                        .code(200)
                        .protocol(Protocol.HTTP_1_1)
                        .message("OK")
                        .body(resultObject.toString().toResponseBody(response.body!!.contentType()))
                        .build()
                } else if (JSONObject(r).has("error")) {
                    /**
                     * Generate an error result.
                     * HTTP/1.1 500 Bad server day
                     * Content-type: application/json
                     * {"message": "unknown error"}
                     */
                    resultObject.put("message", "unknown error")
                    response.newBuilder()
                        .code(500)
                        .protocol(Protocol.HTTP_1_1)
                        .message("ERROR")
                        .body(resultObject.toString().toResponseBody(response.body!!.contentType()))
                        .build()
                }
            }
            response
        }
        .build()

    @OtherInterceptorOkHttpClient
    @Provides
    fun provideOtherInterceptorOkHttpClient(sharedPrefStorage: SharedPreferencesStorage): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        .addInterceptor(HttpInterceptor())
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
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)
}