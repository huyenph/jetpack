package com.utildev.jetpack.data.remote

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.data.remote.response.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Query

typealias GenericResponse<S> = NetworkResponse<S, ErrorResponse>

interface ApiService {
    @GET("questions?key=Ll6gQIUA9QmrAmIknwxK3A((&pagesize=10")
    suspend fun getQuestions(
        @Query("site") site: String,
        @Query("page") page: Int
    ): GenericResponse<JsonObject>
}