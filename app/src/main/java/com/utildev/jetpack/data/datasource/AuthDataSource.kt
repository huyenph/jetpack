package com.utildev.jetpack.data.datasource

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.ApiService
import com.utildev.jetpack.data.remote.GenericResponse
import com.utildev.jetpack.di.AuthNetworkService
import com.utildev.jetpack.domain.repository.AuthRepository
import com.utildev.jetpack.domain.request.auth.UserRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    @AuthNetworkService private val apiService: ApiService
) : AuthRepository {
    override suspend fun fetchRoles(): GenericResponse<JsonObject> =
        withContext(Dispatchers.Default) {
            apiService.fetchRoles()
        }

    override suspend fun createUser(userRequest: UserRequest): GenericResponse<JsonObject> =
        withContext(Dispatchers.Default) {
            apiService.createUser(userRequest)
        }
}