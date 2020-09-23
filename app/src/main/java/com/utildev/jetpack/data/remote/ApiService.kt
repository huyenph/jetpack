package com.utildev.jetpack.data.remote

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.domain.model.request.auth.LoginRequest
import com.utildev.jetpack.domain.model.request.auth.UserRequest
import com.utildev.jetpack.domain.model.response.ErrorResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

typealias GenericResponse<S> = NetworkResponse<S, ErrorResponse>

interface ApiService {
    @GET("role")
    suspend fun fetchRoles(): GenericResponse<JsonObject>

    @POST("user")
    suspend fun createUser(@Body userRequest: UserRequest): GenericResponse<JsonObject>

    @POST("user/login")
    suspend fun login(@Body loginRequest: LoginRequest): GenericResponse<JsonObject>
}