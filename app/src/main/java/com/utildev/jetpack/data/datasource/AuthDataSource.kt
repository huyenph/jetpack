package com.utildev.jetpack.data.datasource

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.ApiService
import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.di.AuthNetworkService
import com.utildev.jetpack.domain.response.ErrorResponse
import com.utildev.jetpack.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    @AuthNetworkService private val apiService: ApiService
) : AuthRepository {
    override suspend fun fetchRoles(): NetworkResponse<JsonObject, ErrorResponse> =
        withContext(Dispatchers.Default) {
            apiService.fetchRoles()
        }
}