package com.utildev.jetpack.presentation.activity.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.jetpack.BR
import com.utildev.jetpack.R
import com.utildev.jetpack.databinding.ActivityAuthBinding
import com.utildev.jetpack.domain.response.role.RoleItem
import com.utildev.jetpack.presentation.base.BaseActivity
import com.utildev.jetpack.presentation.base.BaseAdapter
import com.utildev.jetpack.presentation.fragment.auth.RoleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>() {
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private lateinit var roleAdapter: RoleAdapter
    private lateinit var roleLayoutManager: GridLayoutManager
    private var roles: ArrayList<RoleItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel.roles.observe(this, Observer {
            if (it != null) {
                roles.addAll(it)
//                roleAdapter.set(roles)
//                roleAdapter.notifyDataSetChanged()
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

//        roleLayoutManager = GridLayoutManager(this, 3)
//        roleAdapter = RoleAdapter()
    }
}