package com.utildev.jetpack.domain.usecase

import com.google.gson.JsonObject
import com.utildev.jetpack.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private var authRepository: AuthRepository
) {
    suspend fun getQuestions(site: String, page: Int): JsonObject =
        authRepository.getQuestions(site, page)
}