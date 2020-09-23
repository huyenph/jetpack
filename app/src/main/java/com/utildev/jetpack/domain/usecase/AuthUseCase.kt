package com.utildev.jetpack.domain.usecase

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.GenericResponse
import com.utildev.jetpack.domain.repository.AuthRepository
import com.utildev.jetpack.domain.entity.request.auth.UserRequest
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private var authRepository: AuthRepository
) {
    suspend fun fetchRoles(): GenericResponse<JsonObject> =
        authRepository.fetchRoles()

    suspend fun createUser(userRequest: UserRequest): GenericResponse<JsonObject> =
        authRepository.createUser(userRequest)
}