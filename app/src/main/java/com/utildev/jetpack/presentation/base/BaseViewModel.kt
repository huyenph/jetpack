package com.utildev.jetpack.presentation.base

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.utildev.jetpack.data.local.storage.Storage
import com.utildev.jetpack.data.remote.ApiClient
import java.lang.reflect.Type

abstract class BaseViewModel @ViewModelInject constructor(
 protected val storage: Storage
) : ViewModel(), ApiClient.ResponseListener {
    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        TODO("Not yet implemented")
    }

    override fun onFailure(code: Int, type: Type?) {
        TODO("Not yet implemented")
    }

    override fun onNext(code: Int) {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        super.onCleared()
    }
}