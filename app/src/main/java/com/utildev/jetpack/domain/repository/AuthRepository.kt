package com.utildev.jetpack.domain.repository

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.GenericResponse

interface AuthRepository {
    suspend fun fetchRoles(): GenericResponse<JsonObject>
    suspend fun createUser(userRequest: String): GenericResponse<JsonObject>
}