package com.utildev.jetpack.domain.repository

import com.google.gson.JsonObject
import io.reactivex.Observable

interface AuthRepository {
    fun getQuestions(site: String, page: Int): Observable<JsonObject>
}