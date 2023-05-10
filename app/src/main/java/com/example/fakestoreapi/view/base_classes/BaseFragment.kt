package com.example.fakestoreapi.view.base_classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    private var _binding: DB? = null
    val binding: DB
        get() = _binding as DB
    protected abstract  var viewModel: VM?
    protected abstract val layoutIdFragment: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutIdFragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}