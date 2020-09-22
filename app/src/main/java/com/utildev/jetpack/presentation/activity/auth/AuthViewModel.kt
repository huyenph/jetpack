package com.utildev.jetpack.presentation.activity.auth

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utildev.jetpack.common.SingleLiveData
import com.utildev.jetpack.di.GsonBuilderLenient
import com.utildev.jetpack.domain.request.auth.RoleRequest
import com.utildev.jetpack.domain.request.auth.UserRequest
import com.utildev.jetpack.domain.response.BaseResponse
import com.utildev.jetpack.domain.response.ErrorResponse
import com.utildev.jetpack.domain.response.role.RoleResponse
import com.utildev.jetpack.domain.response.role.RoleItem
import com.utildev.jetpack.domain.usecase.AuthUseCase
import com.utildev.jetpack.presentation.base.BaseViewModel
import java.lang.reflect.Type

class AuthViewModel @ViewModelInject constructor(
    private val authUseCase: AuthUseCase,
    @GsonBuilderLenient private val gson: Gson
) : BaseViewModel() {
    var roles: MutableLiveData<ArrayList<RoleItem>> = MutableLiveData()
    var signUpResult: SingleLiveData<Boolean> = SingleLiveData()

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        try {
            if (code == 1) {
                val role = gson.fromJson(response, type) as RoleResponse
                roles.value = role.items
            } else if (code == 2) {
                val baseResponse = gson.fromJson(response, type) as BaseResponse
                signUpResult.setValue(baseResponse.code == 201)
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
                object : TypeToken<RoleResponse>() {}.type,
                authUseCase.fetchRoles()
            )
        }
    }

    fun createUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        roles: ArrayList<RoleRequest>
    ) {
        val userRequest = UserRequest(
            firstName,
            lastName,
            email,
            password,
            roles
        )
        launchDataLoad {
            apiClient.request(
                2,
                object : TypeToken<BaseResponse>() {}.type,
                authUseCase.createUser(userRequest)
            )
        }
    }
}