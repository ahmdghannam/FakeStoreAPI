package com.example.fakestoreapi.view.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.view.cart.CartViewModel
import com.example.fakestoreapi.view.categories.CategoriesViewModel
import com.example.fakestoreapi.view.home.HomeViewModel
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
            modelClass.isAssignableFrom(CategoriesViewModel::class.java)->{
                return CategoriesViewModel(sharedPreferencesUtil) as T
            }
            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                return CartViewModel(sharedPreferencesUtil) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(sharedPreferencesUtil) as T
            }
            else -> throw Exception("view model not found")
        }
    }
}