package com.example.fakestoreapi.view.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.model.localdata.SharedPreferencesUtil
import com.example.fakestoreapi.view.login.LoginViewModel
import com.example.fakestoreapi.view.product_details.ProductDetailsViewModel
import com.example.fakestoreapi.view.profile.ProfileViewModel

class CustomViewModelFactory(private val sharedPreferencesUtil: SharedPreferencesUtil) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        when{
            modelClass.isAssignableFrom(LoginViewModel::class.java)->{
                return LoginViewModel(sharedPreferencesUtil) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java)->{
                return ProfileViewModel(sharedPreferencesUtil) as T
            }
            modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)->{
                return ProductDetailsViewModel(sharedPreferencesUtil) as T
            }
            else -> throw Exception("view model not found")
        }
    }
}