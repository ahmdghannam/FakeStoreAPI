package com.example.fakestoreapi.view.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentProfileBinding
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.base_classes.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override val layoutIdFragment: Int = R.layout.fragment_profile

    @Inject
    @Named("second_string")
    lateinit var injectedField: String
    override fun setupViewModel() {
        binding.viewmodel = viewModel
    }

    override fun addToastCallBack() {
        createToast(injectedField)
        viewModel?.toastMessage?.observe(viewLifecycleOwner) {
            it?.let { s ->
                createToast(s)
            }
        }
    }

}