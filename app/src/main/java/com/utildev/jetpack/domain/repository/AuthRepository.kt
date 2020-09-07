package com.utildev.jetpack.domain.repository

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.domain.response.ErrorResponse

interface AuthRepository {
    suspend fun fetchRoles(): NetworkResponse<JsonObject, ErrorResponse>
}