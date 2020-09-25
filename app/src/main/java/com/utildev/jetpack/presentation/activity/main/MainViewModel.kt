package com.utildev.jetpack.presentation.activity.main

import androidx.hilt.lifecycle.ViewModelInject
import com.google.gson.Gson
import com.utildev.jetpack.data.local.storage.SharedPreferencesStorage
import com.utildev.jetpack.di.GsonBuilderLenient
import com.utildev.jetpack.presentation.base.BaseViewModel

class MainViewModel @ViewModelInject constructor(
    private val storage: SharedPreferencesStorage,
    @GsonBuilderLenient private val gson: Gson,
) : BaseViewModel(storage, gson) {

}