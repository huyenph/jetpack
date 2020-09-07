package com.utildev.jetpack.presentation.activity.auth

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utildev.jetpack.di.GsonBuilderLenient
import com.utildev.jetpack.domain.response.ErrorResponse
import com.utildev.jetpack.domain.response.role.Role
import com.utildev.jetpack.domain.response.role.RoleItem
import com.utildev.jetpack.domain.usecase.AuthUseCase
import com.utildev.jetpack.presentation.base.BaseViewModel
import java.lang.reflect.Type

class AuthViewModel @ViewModelInject constructor(
    private val authUseCase: AuthUseCase,
    @GsonBuilderLenient private val gson: Gson
) : BaseViewModel() {
    var roles: MutableLiveData<ArrayList<RoleItem>> = MutableLiveData()

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        try {
            if (code == 1) {
                val role = gson.fromJson(response, type) as Role
                roles.value = role.items
            }
        } catch (e: Exception) {
            Log.d("aaa", "onSuccess: $e")
        }

    }

    override fun onFailure(code: Int, errorResponse: ErrorResponse) {
        super.onFailure(code, errorResponse)
    }

    override fun onNetworkError(code: Int) {
        super.onNetworkError(code)
    }

    override fun onUnknownError(code: Int) {
        super.onUnknownError(code)
    }

    fun fetchRoles() {
        launchDataLoad {
            apiClient.request(
                1,
                object : TypeToken<Role>() {}.type,
                authUseCase.fetchRoles()
            )
        }
    }
}