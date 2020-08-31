package com.utildev.jetpack.presentation.activity

import androidx.hilt.lifecycle.ViewModelInject
import com.google.gson.reflect.TypeToken
import com.utildev.jetpack.domain.model.QuestionResponse
import com.utildev.jetpack.domain.usecase.AuthUseCase
import com.utildev.jetpack.presentation.base.BaseViewModel

class MainViewModel @ViewModelInject constructor(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {

    fun onClick() {
        launchDataLoad {
            apiClient.request(
                1,
                object : TypeToken<QuestionResponse>() {}.type,
                authUseCase.getQuestions("stackoverflow", 1)
            )
        }
    }
}