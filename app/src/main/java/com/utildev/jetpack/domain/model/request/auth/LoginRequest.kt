package com.utildev.jetpack.domain.model.request.auth

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.BaseModel

data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
) : BaseModel()