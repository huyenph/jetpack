package com.utildev.jetpack.data.remote

import com.google.gson.annotations.SerializedName
import com.utildev.jetpack.domain.model.BaseModel

class HttpError: BaseModel() {
    @SerializedName("message")
    var message:String? = null
    get() = if (field.isNullOrEmpty()) "fail" else field

}