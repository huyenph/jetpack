package com.utildev.jetpack.domain.model.response.role

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.BaseModel

data class RoleResponse(
    @SerializedName("message") val message: String? = null,
    @SerializedName("items") val items: ArrayList<RoleItem>? = null
) : BaseModel()