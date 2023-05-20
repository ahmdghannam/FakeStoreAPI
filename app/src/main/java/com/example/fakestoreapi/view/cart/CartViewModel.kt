package com.example.fakestoreapi.view.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestoreapi.model.dto.CartProduct
import com.example.fakestoreapi.model.dto.CartResponse
import com.example.fakestoreapi.model.dto.Product
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.model.repository.RepositoryImpl
import com.example.fakestoreapi.view.base_classes.BaseViewModel
import com.example.fakestoreapi.view.categories.CartRecyclerItem
import com.example.fakestoreapi.view.core.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CartViewModel(
    sharedPreferencesUtil: SharedPreferencesUtil
) : BaseViewModel(), CartClickListener {
    override val repository: Repository = RepositoryImpl(sharedPreferencesUtil)

    private val _cartItems = MutableLiveData<List<CartRecyclerItem>>()
    val cartItems: LiveData<List<CartRecyclerItem>>
        get() = _cartItems

    init {
        getAllCarts()
    }

    private fun getAllCarts() {
        repository.getAllCarts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onFailure)
            .addToCompositeDisposable()
    }

    private fun onSuccess(carts: List<CartResponse>) {
        val list = mutableListOf<CartRecyclerItem>()
        list+= CartRecyclerItem.TitleItem("All of items in cart shown here ")
        extractCartRecyclerItemsAndAddItToList(carts, list)
        Thread.sleep(1000)
        _cartItems.postValue(list)
        _fragmentState.postValue(State.Success("carts fetched"))
        list.toPrint()
    }

    fun MutableList<CartRecyclerItem>.toPrint(){
        this.forEach {
           Log.i("viewmodel", it.toData().toString())
        }

    }

    private fun extractCartRecyclerItemsAndAddItToList(
        carts: List<CartResponse>,
        list: MutableList<CartRecyclerItem>
    ) {
        carts.forEach { cartResponse ->
            list += CartRecyclerItem.SubTitleItem(cartResponse.date.toString())
            addProducts(cartResponse, list)
        }
    }

    private fun addProducts(
        cartResponse: CartResponse,
        list: MutableList<CartRecyclerItem>
    ) {
        cartResponse.products?.forEach { product ->
            product?.let {
                repository.getProductById(product.productId!!).subscribe(
                    {productResponse->
                        Log.i("viewmodel", "product response: ${productResponse.toString()} ")
                        productResponse.let {
                            val cartProduct = composeCartProduct(productResponse, product)
                            list += CartRecyclerItem.ProductItem(cartProduct)
                        }
                    }, {}).addToCompositeDisposable()
            }
        }
    }

    private fun getProductResponse(productSafe: Product): ProductResponse? {
        var productResponse: ProductResponse? = null

        return productResponse
    }

    private fun composeCartProduct(
        productResponse: ProductResponse?,
        product: Product
    ) = CartProduct(
        title = productResponse?.title.toString(),
        price = productResponse?.price.toString(),
        image = productResponse?.image.toString(),
        numberOfCopies = product.quantity.toString()
    )

    private fun onFailure(throwable: Throwable) {
        _fragmentState.postValue(State.Error("cannot get carts"))
        _toastMessage.postValue("cannot get carts")
    }

    override fun onProductClickListener() {

    }


}