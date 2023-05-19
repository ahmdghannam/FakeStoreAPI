package com.example.fakestoreapi.view.profile

import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentProfileBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import com.example.fakestoreapi.view.core.CustomViewModelFactory

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override var viewModel: ProfileViewModel? = null
    override val layoutIdFragment: Int = R.layout.fragment_profile


    override fun setupViewModel() {
        val customViewModelFactory = CustomViewModelFactory(
            SharedPreferencesUtil(requireContext())
        )
        viewModel = ViewModelProvider(this, customViewModelFactory)[ProfileViewModel::class.java]
        binding.viewmodel = viewModel
    }

    override fun addToastCallBack() {
       viewModel?.toastMessage?.observe(viewLifecycleOwner){
           it?.let { s ->
               createToast(s)
           }
       }
    }

}