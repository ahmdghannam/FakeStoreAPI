package com.example.fakestoreapi.view.categories

import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentCategoriesBinding
import com.example.fakestoreapi.model.localdata.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import com.example.fakestoreapi.view.core.CustomViewModelFactory

class CaterogriesFragment : BaseFragment<FragmentCategoriesBinding, CategoriesViewModel>() {
    override var viewModel: CategoriesViewModel? = null

    override val layoutIdFragment: Int = R.layout.fragment_categories

    override fun setupViewModel() {
        val customViewModelFactory = CustomViewModelFactory(
            SharedPreferencesUtil(requireContext())
        )
        viewModel = ViewModelProvider(this, customViewModelFactory)[CategoriesViewModel::class.java]
        binding.viewmodel = viewModel
    }

    override fun addToastCallBack() {
        viewModel?.toastMessage?.observe(this){
            createToast(it)
        }
    }
}