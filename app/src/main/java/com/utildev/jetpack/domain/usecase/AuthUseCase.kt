package com.utildev.jetpack.domain.usecase

import com.google.gson.JsonObject
import com.utildev.jetpack.domain.repository.AuthRepository
import io.reactivex.Observable
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {
    fun getQuestions(site: String, page: Int): Observable<JsonObject> =
        authRepository.getQuestions(site, page)
}