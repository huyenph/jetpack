package com.utildev.jetpack.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utildev.jetpack.R
import com.utildev.jetpack.data.local.storage.SharedPreferencesStorage
import com.utildev.jetpack.data.remote.ApiClient
import com.utildev.jetpack.databinding.ActivityMainBinding
import com.utildev.jetpack.domain.model.QuestionResponse
import com.utildev.jetpack.domain.usecase.AuthUseCase
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = mainViewModel
//        sharedPrefStorage.setString("token", "KCTJhLJ5*JRozzNhBK20og((")

//        authUseCase.getQuestions("stackoverflow", 1)


    }
}