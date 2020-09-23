package com.utildev.jetpack.domain.entity.response

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.BaseModel

data class BaseResponse(
    @SerializedName("message") val message: String? = null,
    @SerializedName("code") val code: Int
) : BaseModel()