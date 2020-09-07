package com.utildev.jetpack.data.remote

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.domain.response.ErrorResponse
import retrofit2.http.GET

typealias GenericResponse<S> = NetworkResponse<S, ErrorResponse>

interface ApiService {
    @GET("role")
    suspend fun fetchRoles(): GenericResponse<JsonObject>
}