package com.utildev.jetpack.data.datasource

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.ApiService
import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.data.remote.helper.HttpError
import com.utildev.jetpack.data.remote.response.ErrorResponse
import com.utildev.jetpack.di.OtherNetworkService
import com.utildev.jetpack.domain.model.QuestionResponse
import com.utildev.jetpack.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    @OtherNetworkService private val apiService: ApiService
) : AuthRepository {
    override suspend fun getQuestions(
        site: String,
        page: Int
    ): NetworkResponse<JsonObject, ErrorResponse> =
        withContext(Dispatchers.Default) {
            apiService.getQuestions(site, page)
        }
}