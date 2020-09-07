package com.utildev.jetpack.presentation.fragment.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.jetpack.BR
import com.utildev.jetpack.R
import com.utildev.jetpack.common.SpaceItemDecoration
import com.utildev.jetpack.databinding.FragmentSignUpBinding
import com.utildev.jetpack.domain.request.auth.RoleRequest
import com.utildev.jetpack.domain.response.role.RoleItem
import com.utildev.jetpack.presentation.activity.auth.AuthActivity
import com.utildev.jetpack.presentation.activity.auth.AuthViewModel
import com.utildev.jetpack.presentation.base.BaseAdapter
import com.utildev.jetpack.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, AuthViewModel>(),
    BaseAdapter.AdapterListener {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var roleAdapter: RoleAdapter
    private lateinit var roleLayoutManager: GridLayoutManager
    private val selectedRoles: ArrayList<RoleRequest> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun layoutId(): Int = R.layout.fragment_sign_up

    override fun bindingVariable(): Int? = BR.vm

    override fun viewModel(): AuthViewModel? = (activity as AuthActivity).authViewModel

    override fun init(view: View) {
        view.fmSignUp_tvSignUp.setOnClickListener(this)
        view.fmSignUp_tvSignIn.setOnClickListener(this)

        roleLayoutManager = GridLayoutManager(context, 2)
        roleAdapter = RoleAdapter(view.fmSignUp_rvRole, roleLayoutManager, this)
        roleAdapter.set((activity as AuthActivity).roles)
        val spacingInPixel = resources.getDimensionPixelSize(R.dimen._10dp)
        view.fmSignUp_rvRole.run {
            layoutManager = roleLayoutManager
            adapter = roleAdapter
            setHasFixedSize(true)
        }
        view.fmSignUp_rvRole.addItemDecoration(SpaceItemDecoration(spacingInPixel, 2, false))
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.fmSignUp_tvSignUp -> {
                    (activity as AuthActivity).authViewModel.createUser(
                        viewDataBinding().fmSignUpEtFirstName.text.trim().toString(),
                        viewDataBinding().fmSignUpEtLastName.text.trim().toString(),
                        viewDataBinding().fmSignUpEtEmail.text.trim().toString(),
                        viewDataBinding().fmSignUpEtPassword.text.trim().toString(),
                        selectedRoles
                    )
//                    selectedRoles.forEach {
//                        Log.d("aaa", "onClick: $it")
//                    }
                }
                R.id.fmSignUp_tvSignIn -> v.findNavController().popBackStack()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(`object`: Any, position: Int) {
        if (`object` is RoleItem) {
            val roleRequest = RoleRequest(
                `object`.id!!,
                `object`.code!!,
                `object`.title!!
            )
            if (selectedRoles.contains(roleRequest)) {
                selectedRoles.remove(roleRequest)
            } else {
                selectedRoles.add(roleRequest)
            }
        }
    }

    override fun onItemLongClick(`object`: Any, position: Int): Boolean = false

    override fun onLoadMore() {}
}