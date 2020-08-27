package com.utildev.jetpack.data.datasource

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.ApiService
import com.utildev.jetpack.di.OtherNetworkService
import com.utildev.jetpack.domain.repository.AuthRepository
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    @OtherNetworkService private val apiService: ApiService
) : AuthRepository {
    override suspend fun getQuestions(site: String, page: Int): JsonObject =
        apiService.getQuestions(site, page)
}