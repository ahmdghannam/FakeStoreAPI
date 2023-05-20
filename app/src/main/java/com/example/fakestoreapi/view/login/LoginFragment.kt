package com.example.fakestoreapi.view.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentLoginBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    override val layoutIdFragment = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        addToastCallBack()
    }

    override fun setupViewModel() {
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