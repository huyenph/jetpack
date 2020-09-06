package com.utildev.jetpack.data.remote.response.role

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.model.BaseModel

data class Role(
    @SerializedName("message") val message: String? = null,
    @SerializedName("items") val items: ArrayList<RoleItem>? = null
) : BaseModel()