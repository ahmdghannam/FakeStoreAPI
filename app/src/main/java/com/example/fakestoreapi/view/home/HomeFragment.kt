package com.example.fakestoreapi.view.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentHomeBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import com.example.fakestoreapi.view.core.CustomViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override var viewModel: HomeViewModel? = null
    override val layoutIdFragment = R.layout.fragment_home
    override fun setupViewModel() {
        val sharedPreferencesUtil = SharedPreferencesUtil(requireActivity().applicationContext)
        val customViewModelFactory = CustomViewModelFactory(sharedPreferencesUtil)
        viewModel = ViewModelProvider(this, customViewModelFactory)[HomeViewModel::class.java]
        binding.viewmodel = viewModel
    }

    override fun addToastCallBack() {
        viewModel?.toastMessage?.observe(this) {
            createToast(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerAdapter()
    }

    private fun setupRecyclerAdapter() {
        val layoutManager = GridLayoutManager(
            requireContext(),
            2 // every row has two parts
        )

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) {
                    2  // this item will fill two parts, which means will match parent
                } else {
                    1 // this will fill one part,which is half of the parent
                }
            }
        }
        binding.recyclerviewHome.layoutManager = layoutManager
        binding.recyclerviewHome.adapter = HomeRecyclerAdapter(mutableListOf(), viewModel!!)
    }


}