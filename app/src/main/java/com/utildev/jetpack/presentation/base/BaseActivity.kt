package com.utildev.jetpack.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {
    private lateinit var binding: T

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



}