package com.utildev.jetpack.data.datasource

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.ApiService
import com.utildev.jetpack.di.AuthNetworkService
import com.utildev.jetpack.di.OtherNetworkService
import com.utildev.jetpack.domain.repository.AuthRepository
import io.reactivex.Observable
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    @OtherNetworkService private val apiService: ApiService
) : AuthRepository {
    override fun getQuestions(site: String, page: Int): Observable<JsonObject> =
        apiService.getQuestions(site, page)
}