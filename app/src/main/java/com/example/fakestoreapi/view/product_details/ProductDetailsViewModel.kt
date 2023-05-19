package com.example.fakestoreapi.view.product_details

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

class ProductDetailsViewModel(
    sharedPreferencesUtil: SharedPreferencesUtil
) : BaseViewModel() {
    override val repository: Repository = RepositoryImpl(sharedPreferencesUtil)
    private val _category = MutableLiveData<String>()
    val category: LiveData<String>
        get() = _category

    private val _productImage = MutableLiveData<String>()
    val productImage: LiveData<String>
        get() = _productImage

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private val _price = MutableLiveData<String>()
    val price: LiveData<String>
        get() = _price

    fun toPrice() = "Price: ${price.value}$"


    private val _rating = MutableLiveData<Double>()
    val rating: LiveData<Double>
        get() = _rating

    fun toRating() = _rating.value?.toInt()

    private val _ratesCounter = MutableLiveData<String>()
    val ratesCounter: LiveData<String>
        get() = _ratesCounter

    fun toRatesCounter() = "${ratesCounter.value} ratings"


    init {
        getProductData()
    }

    private fun getProductData() {
        _fragmentState.postValue(State.Loading)
        repository.getProductById(20)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::onSuccess, ::onFailure)
            .addToCompositeDisposable()
    }

    private fun onSuccess(product: ProductResponse) = product.apply {
        product.category?.let {
            _category.postValue(it)
        }
        product.image?.let {
            _productImage.postValue(it)
        }
        product.title?.let {
            _title.postValue(it)
        }
        product.description?.let {
            _description.postValue(it)
        }
        product.price?.let {
            _price.postValue(it.toString())
        }
        product.rating?.rate?.let {
            _rating.postValue(it)
        }

        product.rating?.count?.let {
            _ratesCounter.postValue(it.toString())
        }
        _fragmentState.postValue(State.Success("product fetched"))
    }


    private fun onFailure(throwable: Throwable) {
        _fragmentState.postValue(State.Error("product not found"))
        _toastMessage.postValue("product not found")
    }


}