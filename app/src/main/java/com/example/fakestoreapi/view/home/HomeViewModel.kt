package com.example.fakestoreapi.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.model.repository.RepositoryImpl
import com.example.fakestoreapi.view.base_classes.BaseViewModel
import com.example.fakestoreapi.view.core.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    sharedPrefsUtil: SharedPreferencesUtil
) : BaseViewModel(),HomeClickListeners {
    override val repository: Repository = RepositoryImpl(sharedPrefsUtil)

    private val _products = MutableLiveData<List<HomeRecyclerItem>>()
    val products: LiveData<List<HomeRecyclerItem>> = _products

    init {
        getAllProducts()
    }

     fun getAllProducts() {
        _fragmentState.postValue(State.Loading)
        repository.getAllProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onFailure)
            .addToCompositeDisposable()
    }

    private fun onSuccess(products: List<ProductResponse>) {
        val recyclerItems = mutableListOf<HomeRecyclerItem>()
        recyclerItems += HomeRecyclerItem.TitleItem("Items have been chooses with love")
        products.forEach {
            recyclerItems += HomeRecyclerItem.ProductItem(it)
        }
        _products.postValue(recyclerItems)
        _fragmentState.postValue(State.Success("fetch products successfully"))
    }

    private fun onFailure(throwable: Throwable) {
        _toastMessage.postValue("cannot fetch products")
        _fragmentState.postValue(State.Error("cannot fetch products"))
    }

    override fun onProductClickListener() {

    }

}