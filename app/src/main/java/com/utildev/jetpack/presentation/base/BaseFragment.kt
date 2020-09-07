package com.utildev.jetpack.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {
    private lateinit var binding: T
    private lateinit var rootView: View

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun bindingVariable(): Int?

    abstract fun viewModel(): V?

    abstract fun init(view: View)

    fun viewDataBinding(): T = binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        if (viewModel() != null) {
            bindingVariable()?.let { binding.setVariable(it, viewModel()) }
            binding.executePendingBindings()
        }
        rootView = binding.root
        init(rootView)
        return rootView
    }
}