package com.example.fakestoreapi.view.cart

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentCartBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import com.example.fakestoreapi.view.core.CustomViewModelFactory

class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>() {
    override var viewModel: CartViewModel? = null
    override val layoutIdFragment = R.layout.fragment_cart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerviewCart.adapter= CartRecyclerAdapter(
            listOf(),
            viewModel!!
        )
    }

    override fun setupViewModel() {
        val customViewModelFactory = CustomViewModelFactory(
            SharedPreferencesUtil(requireContext().applicationContext)
        )
        viewModel = ViewModelProvider(this, customViewModelFactory)[CartViewModel::class.java]
        binding.viewmodel = viewModel
    }

    override fun addToastCallBack() {
        viewModel?.toastMessage?.observe(this){
            createToast(it)
        }
    }
}