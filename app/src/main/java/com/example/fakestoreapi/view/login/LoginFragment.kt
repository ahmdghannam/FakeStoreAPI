package com.example.fakestoreapi.view.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentLoginBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import com.example.fakestoreapi.view.core.CustomViewModelFactory

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override var viewModel: LoginViewModel? = null

    override val layoutIdFragment = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        addToastCallBack()
    }

    override fun setupViewModel() {
        val customViewModelFactory = CustomViewModelFactory(
            SharedPreferencesUtil(requireContext())
        )
        viewModel = ViewModelProvider(this, customViewModelFactory)[LoginViewModel::class.java]
        binding.viewmodel = viewModel
    }

    override fun addToastCallBack() {
        viewModel?.toastMessage?.observe(viewLifecycleOwner) {
            it?.let { s ->
                createToast(s)
            }
        }
    }


}