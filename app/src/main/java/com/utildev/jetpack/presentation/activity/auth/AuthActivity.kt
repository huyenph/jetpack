package com.utildev.jetpack.presentation.activity.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.utildev.jetpack.BR
import com.utildev.jetpack.R
import com.utildev.jetpack.databinding.ActivityAuthBinding
import com.utildev.jetpack.domain.response.role.RoleItem
import com.utildev.jetpack.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>() {
    val authViewModel: AuthViewModel by viewModels()
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

     var roles: ArrayList<RoleItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel.roles.observe(this, {
            if (it != null) {
                roles.addAll(it)
            }
        })
    }

    override fun layoutId(): Int = R.layout.activity_auth

    override fun bindingVariable(): Int? = BR.vm

    override fun viewModel(): AuthViewModel? = authViewModel

    override fun init() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navAuthFragment) as NavHostFragment
        navController = navHostFragment.navController
        authViewModel.fetchRoles()
    }
}