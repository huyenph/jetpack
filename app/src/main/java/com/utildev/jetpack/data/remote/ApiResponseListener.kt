package com.utildev.jetpack.data.remote

import com.google.gson.JsonObject
import com.utildev.jetpack.data.remote.response.ErrorResponse
import java.lang.reflect.Type

interface ApiResponseListener {
    fun onSuccess(code: Int, type: Type?, response: JsonObject)
    fun onFailure(code: Int, errorResponse: ErrorResponse)
    fun onNetworkError(code: Int)
    fun onUnknownError(code: Int)
}