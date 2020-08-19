package com.utildev.jetpack.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utildev.jetpack.R
import com.utildev.jetpack.data.local.storage.SharedPreferencesStorage
import com.utildev.jetpack.data.remote.ApiClient
import com.utildev.jetpack.domain.model.QuestionResponse
import com.utildev.jetpack.domain.usecase.AuthUseCase
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import java.lang.reflect.Type
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ApiClient.ResponseListener {
    @Inject
    lateinit var authUseCase: AuthUseCase
    @Inject
    lateinit var sharedPrefStorage: SharedPreferencesStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPrefStorage.setString("token", "KCTJhLJ5*JRozzNhBK20og((")
        val apiClient = ApiClient(this, CompositeDisposable())
        apiClient.request(
            1,
            object : TypeToken<QuestionResponse>() {}.type,
            authUseCase.getQuestions("stackoverflow", 1)
        )
    }

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        print("code: $code - type: $type - response: $response")
    }

    override fun onFailure(code: Int, type: Type?) {
        print("code: $code - type: $type")
    }

    override fun onNext(code: Int) {
        print("complete")
    }
}