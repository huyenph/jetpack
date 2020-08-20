package com.utildev.jetpack.presentation.base

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.utildev.jetpack.data.local.storage.Storage
import com.utildev.jetpack.data.remote.ApiResponseListener
import java.lang.reflect.Type
import javax.inject.Inject

open class BaseViewModel @ViewModelInject constructor() : ViewModel(), ApiResponseListener {
    @Inject
    lateinit var storage: Storage

    protected val loadingView = ObservableInt(View.GONE)
    protected val messageView = ObservableInt(View.GONE)
    protected val message = ObservableField<String>()
    protected val enableView = ObservableBoolean(false)

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
    }

    override fun onFailure(code: Int, type: Type?) {
    }

    override fun onNext(code: Int) {
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun showLoading() {
        if (loadingView.get() != View.VISIBLE) {
            loadingView.set(View.VISIBLE)
            enableView.set(false)
        }
    }

    fun dismissLoading() {
        if (loadingView.get() == View.VISIBLE) {
            loadingView.set(View.GONE)
            enableView.set(true)
        }
    }

    fun showMessage() {
        if (messageView.get() != View.VISIBLE) {
            messageView.set(View.VISIBLE)
        }
    }

    fun dismissMessage() {
        if (messageView.get() == View.VISIBLE) {
            messageView.set(View.GONE)
        }
    }
}