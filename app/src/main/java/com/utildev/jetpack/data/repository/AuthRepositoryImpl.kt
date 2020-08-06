package com.utildev.jetpack.data.repository

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.ApiService
import com.utildev.jetpack.domain.repository.AuthRepository
import io.reactivex.Observable
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {
    override fun getQuestions(site: String, page: Int): Observable<JsonObject> =
        apiService.getQuestions(site, page)
}