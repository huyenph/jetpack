package com.utildev.jetpack.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(), View.OnClickListener {
    private lateinit var binding: T
    lateinit var rootView: View

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun bindingVariable(): Int?

    abstract fun viewModel(): V?

    abstract fun init(view: View)

    fun viewDataBinding(): T = binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
               handleOnBackPressed()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

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

    open fun handleOnBackPressed() {
        rootView.findNavController().popBackStack()
    }
}