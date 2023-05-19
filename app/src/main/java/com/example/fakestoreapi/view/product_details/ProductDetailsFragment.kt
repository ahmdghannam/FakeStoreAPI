package com.example.fakestoreapi.view.product_details

import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentProductDetailsBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import com.example.fakestoreapi.view.core.CustomViewModelFactory

class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, ProductDetailsViewModel>() {

    override var viewModel: ProductDetailsViewModel? = null
    override val layoutIdFragment = R.layout.fragment_product_details

    override fun setupViewModel() {
        val customViewModelFactory = CustomViewModelFactory(
            SharedPreferencesUtil(requireContext())
        )
        viewModel =
            ViewModelProvider(this, customViewModelFactory)[ProductDetailsViewModel::class.java]
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