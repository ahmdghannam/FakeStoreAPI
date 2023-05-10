package com.example.fakestoreapi.view.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentLoginBinding
import com.example.fakestoreapi.model.localdata.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    private lateinit var sharedPrefsUtil: SharedPreferencesUtil

    override var viewModel: LoginViewModel? = null

    override val layoutIdFragment = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = ViewModelFactory(
            SharedPreferencesUtil(requireContext())
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        binding.viewmodel = viewModel

    }


}