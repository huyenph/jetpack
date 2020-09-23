package com.utildev.jetpack.presentation.base

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.utildev.jetpack.R

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {
    private lateinit var binding: T

    var doubleBackToExitPressedOnce = false

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun bindingVariable(): Int?

    abstract fun viewModel(): V?

    abstract fun init()

    fun viewDataBinding(): T = binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId())
        if (viewModel() != null) {
            bindingVariable()?.let { binding.setVariable(it, viewModel()) }
            binding.executePendingBindings()
        }
        init()
    }

    fun handleOnBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.click_back_to_exit), Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}