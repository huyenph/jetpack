package com.utildev.jetpack.data.remote

import com.google.gson.JsonObject
import java.lang.reflect.Type

interface ApiResponseListener {
    fun onSuccess(code: Int, type: Type?, response: JsonObject)
    fun onFailure(code: Int, type: Type?)
    fun onNext(code: Int)
}