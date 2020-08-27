package com.utildev.jetpack.presentation.activity

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.utildev.jetpack.domain.usecase.AuthUseCase
import com.utildev.jetpack.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {

    fun onClick() {
        launchDataLoad {
            authUseCase.getQuestions("stackoverflow", 1)
        }
    }
}