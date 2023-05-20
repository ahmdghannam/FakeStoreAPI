package com.example.fakestoreapi.view.product_details

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentProductDetailsBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, ProductDetailsViewModel>() {

    override val viewModel: ProductDetailsViewModel by viewModels()
    override val layoutIdFragment = R.layout.fragment_product_details

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