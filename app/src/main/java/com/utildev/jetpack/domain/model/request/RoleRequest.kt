package com.utildev.jetpack.domain.model.request

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.BaseModel

data class RoleRequest(
    @SerializedName("role_id") val roleId: String,
    @SerializedName("code") val code: String,
    @SerializedName("title") val title: String,
) : BaseModel()