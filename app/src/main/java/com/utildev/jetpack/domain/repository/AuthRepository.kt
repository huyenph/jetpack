package com.utildev.jetpack.domain.repository

import com.google.gson.JsonObject

interface AuthRepository {
    suspend fun getQuestions(site: String, page: Int): JsonObject
}