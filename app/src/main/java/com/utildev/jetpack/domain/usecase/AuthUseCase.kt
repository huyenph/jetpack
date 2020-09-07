package com.utildev.jetpack.domain.usecase

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.domain.response.ErrorResponse
import com.utildev.jetpack.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private var authRepository: AuthRepository
) {
    suspend fun fetchRoles(): NetworkResponse<JsonObject, ErrorResponse> =
        authRepository.fetchRoles()
}