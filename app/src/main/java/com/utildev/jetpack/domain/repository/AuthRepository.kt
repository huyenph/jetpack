package com.utildev.jetpack.domain.repository

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.data.remote.helper.HttpError
import com.utildev.jetpack.domain.model.QuestionResponse

interface AuthRepository {
    suspend fun getQuestions(site: String, page: Int): NetworkResponse<QuestionResponse, HttpError>
}