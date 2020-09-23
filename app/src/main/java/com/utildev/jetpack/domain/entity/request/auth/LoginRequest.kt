package com.utildev.jetpack.domain.entity.request.auth

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.BaseModel

data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
) : BaseModel()