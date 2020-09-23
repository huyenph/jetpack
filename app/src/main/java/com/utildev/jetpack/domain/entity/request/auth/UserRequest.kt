package com.utildev.jetpack.domain.entity.request.auth

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.BaseModel

data class UserRequest(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("role") val role: ArrayList<RoleRequest>,
) : BaseModel()