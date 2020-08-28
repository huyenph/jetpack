package com.utildev.jetpack.presentation.activity

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.utildev.jetpack.data.remote.adapter.NetworkResponse
import com.utildev.jetpack.domain.usecase.AuthUseCase
import com.utildev.jetpack.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {

    fun onClick() {
        launchDataLoad {
            val result = authUseCase.getQuestions("stackoverflow", 1)
            when (result) {
                is NetworkResponse.Success -> {
                    Log.d("aaa", "onSuccess: ${result.code} - ${result.body}")
                }
                is NetworkResponse.Failure -> {
                    Log.d("aaa", "onFailure: ${result.code}")
                }
                is NetworkResponse.NetworkError -> {
                    Log.d("aaa", "onNetworkError")
                }
                is NetworkResponse.UnknownError -> {
                    Log.d("aaa", "onUnknownError")
                }
            }
        }
    }
}