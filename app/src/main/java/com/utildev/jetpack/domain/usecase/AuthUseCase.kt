package com.utildev.jetpack.domain.usecase

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.GenericResponse
import com.utildev.jetpack.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private var authRepository: AuthRepository
) {
    suspend fun fetchRoles(): GenericResponse<JsonObject> =
        authRepository.fetchRoles()

    suspend fun createUser(userRequest: String): GenericResponse<JsonObject> =
        authRepository.createUser(userRequest)
}