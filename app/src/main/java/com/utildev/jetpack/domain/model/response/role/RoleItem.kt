package com.utildev.jetpack.domain.model.response.role

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.BaseModel

data class RoleItem(
    @SerializedName("_id") val id: String? = null,
    @SerializedName("code") val code: String? = null,
    @SerializedName("title") val title: String? = null,
    var selected: Boolean = false,
) : BaseModel()