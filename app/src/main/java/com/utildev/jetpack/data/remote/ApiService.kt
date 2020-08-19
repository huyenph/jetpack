package com.utildev.jetpack.data.remote

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("questions?key=Ll6gQIUA9QmrAmIknwxK3A((&pagesize=10")
    fun getQuestions(@Query("site") site: String, @Query("page") page: Int): Observable<JsonObject>
}