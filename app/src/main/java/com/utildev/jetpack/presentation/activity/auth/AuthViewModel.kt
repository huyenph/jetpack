package com.utildev.jetpack.presentation.activity.auth

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utildev.jetpack.common.*
import com.utildev.jetpack.common.state.Event
import com.utildev.jetpack.data.local.storage.SharedPreferencesStorage
import com.utildev.jetpack.di.GsonBuilderLenient
import com.utildev.jetpack.domain.model.request.auth.LoginRequest
import com.utildev.jetpack.domain.model.request.RoleRequest
import com.utildev.jetpack.domain.model.request.auth.UserRequest
import com.utildev.jetpack.domain.model.response.BaseResponse
import com.utildev.jetpack.domain.model.response.ErrorResponse
import com.utildev.jetpack.domain.model.response.auth.LoginResponse
import com.utildev.jetpack.domain.model.response.role.RoleResponse
import com.utildev.jetpack.domain.model.response.role.RoleItem
import com.utildev.jetpack.domain.usecase.AuthUseCase
import com.utildev.jetpack.presentation.base.BaseViewModel
import java.lang.reflect.Type

class AuthViewModel @ViewModelInject constructor(
    private val storage: SharedPreferencesStorage,
    @GsonBuilderLenient private val gson: Gson,
    private val authUseCase: AuthUseCase,
) : BaseViewModel(storage, gson) {
    private val _roles = MutableLiveData<ArrayList<RoleItem>>()
    val roles: MutableLiveData<ArrayList<RoleItem>>
        get() = _roles

    private val _loginResult = MutableLiveData<Event<LoginResponse>>()
    val loginResult: MutableLiveData<Event<LoginResponse>>
        get() = _loginResult

    private val _signUpResult = MutableLiveData<Event<Boolean>>()
    val signUpResult: MutableLiveData<Event<Boolean>>
        get() = _signUpResult

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        try {
            when (code) {
                LOGIN -> {
                    val login = gson.fromJson(response, type) as LoginResponse
                    if (login.code == OK) {
                        storage.setString(SharedPreferencesStorage.TOKEN, login.token)
                    }
                    _loginResult.value = Event(login)
                }
                FETCH_ROLE -> {
                    val role = gson.fromJson(response, type) as RoleResponse
                    _roles.value = role.items
                }
                SIGN_UP -> {
                    val baseResponse = gson.fromJson(response, type) as BaseResponse
                    _signUpResult.value = Event(baseResponse.code == 201)
                }
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
                FETCH_ROLE,
                object : TypeToken<RoleResponse>() {}.type,
                authUseCase.fetchRoles()
            )
        }
    }

    fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        launchDataLoad {
            apiClient.request(
                LOGIN,
                object : TypeToken<LoginResponse>() {}.type,
                authUseCase.login(loginRequest)
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
        _signUpResult.value = Event(true)
//        launchDataLoad {
//            apiClient.request(
//                SIGN_UP,
//                object : TypeToken<BaseResponse>() {}.type,
//                authUseCase.createUser(userRequest)
//            )
//        }
    }
}