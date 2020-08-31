package com.utildev.jetpack.domain.usecase

import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.data.remote.helper.HttpError
import com.utildev.jetpack.domain.model.QuestionResponse
import com.utildev.jetpack.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private var authRepository: AuthRepository
) {
    suspend fun getQuestions(site: String, page: Int): NetworkResponse<QuestionResponse, HttpError> =
        authRepository.getQuestions(site, page)
}