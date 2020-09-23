package com.utildev.jetpack.domain.repository

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.GenericResponse
import com.utildev.jetpack.domain.model.request.auth.LoginRequest
import com.utildev.jetpack.domain.model.request.auth.UserRequest

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): GenericResponse<JsonObject>
    suspend fun fetchRoles(): GenericResponse<JsonObject>
    suspend fun createUser(userRequest: UserRequest): GenericResponse<JsonObject>
}