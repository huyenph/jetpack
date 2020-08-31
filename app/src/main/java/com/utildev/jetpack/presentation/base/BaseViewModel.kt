package com.utildev.jetpack.presentation.base

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.utildev.jetpack.data.local.storage.Storage
import com.utildev.jetpack.data.remote.ApiClient
import com.utildev.jetpack.data.remote.ApiResponseListener
import com.utildev.jetpack.data.remote.response.ErrorResponse
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

@Suppress("LeakingThis")
open class BaseViewModel @ViewModelInject constructor() : ViewModel(), ApiResponseListener {
    @Inject
    lateinit var storage: Storage

    val messageString = ObservableField<String>()
    val loadingView = ObservableInt(View.GONE)
    val messageView = ObservableInt(View.GONE)
    val enableView = ObservableBoolean(true)

    protected val apiClient = ApiClient(this)

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        Log.d("aaa", "onSuccess: $code - $response")
    }

    override fun onFailure(code: Int, errorResponse: ErrorResponse) {
        Log.d("aaa", "onFailure: $code - $errorResponse")
    }

    override fun onNetworkError(code: Int) {
        Log.d("aaa", "onNetworkError: $code")
    }

    override fun onUnknownError(code: Int) {
        Log.d("aaa", "onUnknownError: $code")
    }

    protected fun launchDataLoad(block: suspend () -> Unit): Unit {
        viewModelScope.launch {
            try {
                showLoading()
                dismissMessage()
                block()
            } catch (e: Throwable) {
                Log.d("aaa", "launchDataLoad: $e")
                dismissLoading()
                showMessage("Error")
            } finally {
                dismissLoading()
            }
        }
    }

    fun showLoading() {
        if (loadingView.get() == View.GONE) {
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

    fun showMessage(message: String) {
        messageString.set(message)
        if (messageView.get() == View.GONE) {
            messageView.set(View.VISIBLE)
        }
    }

    fun dismissMessage() {
        messageString.set(null)
        if (messageView.get() == View.VISIBLE) {
            messageView.set(View.GONE)
        }
    }
}