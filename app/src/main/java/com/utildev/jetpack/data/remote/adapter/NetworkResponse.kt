package com.utildev.jetpack.data.remote.adapter

import java.io.IOException

sealed class NetworkResponse<out S : Any, out E : Any> {
    /**
     * Success response with body
     */
    data class Success<S : Any>(val body: S, val code: Int) : NetworkResponse<S, Nothing>()

    /**
     * Failure response with body
     */
    data class Failure<E : Any>(val body: E, val code: Int) : NetworkResponse<Nothing, E>()

    /**
     * Network error
     */
    data class NetworkError(val code: Int, val error: IOException) : NetworkResponse<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val code: Int, val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
}