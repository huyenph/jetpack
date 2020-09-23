package com.utildev.jetpack.domain.model.response.auth

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.BaseModel

data class LoginResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("token") val token: String,
) : BaseModel()