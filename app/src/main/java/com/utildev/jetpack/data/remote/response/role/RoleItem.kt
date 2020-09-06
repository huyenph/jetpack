package com.utildev.jetpack.data.remote.response.role

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.model.BaseModel

data class RoleItem(
    @SerializedName("_id") val id: String? = null,
    @SerializedName("code") val code: String? = null,
    @SerializedName("title") val title: String? = null
) : BaseModel()