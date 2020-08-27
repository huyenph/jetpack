package com.utildev.jetpack.presentation.base

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.utildev.jetpack.data.local.storage.Storage
import com.utildev.jetpack.data.remote.ApiResponseListener
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

open class BaseViewModel @ViewModelInject constructor() : ViewModel(), ApiResponseListener {
    @Inject
    lateinit var storage: Storage

    protected val message = MutableLiveData<String>()
//    val message: LiveData<String>
//        get() = _message

    protected val loadingView = ObservableInt(View.GONE)
//    protected val messageVisibility: ObservableInt = message.observe(this, Observer {
//        return@Observer ObservableInt(View.GONE)
//    })
    protected val messageString: LiveData<String> = message.map { it }



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

    protected fun launchDataLoad(block: suspend () -> Unit): Unit {
        viewModelScope.launch {
            try {
                showLoading()
                block()
            } catch (e: Throwable) {
                dismissLoading()
                message.value = "Error"
            } finally {
                dismissLoading()
            }
        }
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
//        if (messageView.get() != View.VISIBLE) {
//            messageView.set(View.VISIBLE)
//        }
    }

    fun dismissMessage() {
//        if (messageView.get() == View.VISIBLE) {
//            messageView.set(View.GONE)
//        }
    }
}