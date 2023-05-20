package com.example.fakestoreapi.view.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentCategoriesBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, CategoriesViewModel>() {
    override val viewModel: CategoriesViewModel by viewModels()

    override val layoutIdFragment: Int = R.layout.fragment_categories
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewCategories.adapter =
            CategoriesRecyclerAdapter(mutableListOf(), viewModel!!)
    }

    override fun setupViewModel() {
        binding.viewmodel = viewModel
    }

    override fun addToastCallBack() {
        viewModel?.toastMessage?.observe(this) {
            createToast(it)
        }
    }
}