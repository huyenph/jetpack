package com.utildev.jetpack.presentation.activity.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.utildev.jetpack.R

class AuthActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navAuthFragment) as NavHostFragment
        navController =navHostFragment.navController
    }
}