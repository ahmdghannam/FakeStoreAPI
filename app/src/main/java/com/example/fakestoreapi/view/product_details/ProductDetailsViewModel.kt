package com.example.fakestoreapi.view.product_details

import com.example.fakestoreapi.model.localdata.SharedPreferencesUtil
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.model.repository.RepositoryImpl
import com.example.fakestoreapi.view.base_classes.BaseViewModel

class ProductDetailsViewModel(sharedPreferencesUtil: SharedPreferencesUtil) : BaseViewModel() {
    override val repository: Repository = RepositoryImpl(sharedPreferencesUtil)



}