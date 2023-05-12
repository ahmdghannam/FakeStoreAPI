package com.example.fakestoreapi.view.base_classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        addToastCallBack()
    }
    fun createToast(s:String){
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

    abstract fun setupViewModel()
    abstract fun addToastCallBack()

}