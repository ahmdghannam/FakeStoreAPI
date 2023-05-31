package com.example.fakestoreapi.view.categories

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentCategoriesBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import com.example.fakestoreapi.view.core.CustomViewModelFactory

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, CategoriesViewModel>() {
    override var viewModel: CategoriesViewModel? = null

    override val layoutIdFragment: Int = R.layout.fragment_categories
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerviewCategories.adapter =
            CategoriesRecyclerAdapter(mutableListOf(), viewModel!!)
    }

    override fun setupViewModel() {
        val customViewModelFactory = CustomViewModelFactory(
            SharedPreferencesUtil(requireContext().applicationContext)
        )
        viewModel = ViewModelProvider(this, customViewModelFactory)[CategoriesViewModel::class.java]
        binding.viewmodel = viewModel
    }

    override fun addToastCallBack() {
        viewModel?.toastMessage?.observe(this) {
            createToast(it)
        }
    }
}