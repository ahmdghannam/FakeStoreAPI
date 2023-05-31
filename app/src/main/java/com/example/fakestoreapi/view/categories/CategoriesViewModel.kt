package com.example.fakestoreapi.view.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestoreapi.R
import com.example.fakestoreapi.model.dto.CategoryItem
import com.example.fakestoreapi.model.recyclerItems.RecyclerItem
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.model.repository.RepositoryImpl
import com.example.fakestoreapi.view.base_classes.BaseViewModel
import com.example.fakestoreapi.view.core.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class CategoriesViewModel(
    sharedPreferencesUtil: SharedPreferencesUtil
) : BaseViewModel(), CategoriesClickListener {

    override val repository: Repository = RepositoryImpl(sharedPreferencesUtil)

    private val _categories = MutableLiveData<List<RecyclerItem<Any>>>()
    val categories: LiveData<List<RecyclerItem<Any>>>
        get() = _categories

    init {
        getAllCategories()
    }

    private fun getAllCategories() {
        _fragmentState.postValue(State.Loading)
        repository.getAllCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onFailure)
            .addToCompositeDisposable()
    }

    private fun onSuccess(categoriesNames: List<String>) {
        val categoriesRecyclerItems: List<RecyclerItem<Any>> = listOf(
            RecyclerItem("filter the items"),
            RecyclerItem(CategoryItem(categoriesNames[ELECTRONICS], R.drawable.electronics)),
            RecyclerItem(CategoryItem(categoriesNames[JEWELERY], R.drawable.jewerly)),
            RecyclerItem(CategoryItem(categoriesNames[MENS], R.drawable.mens)),
            RecyclerItem(CategoryItem(categoriesNames[WOMENS], R.drawable.womens)),
        )
        _categories.postValue(categoriesRecyclerItems)
        _fragmentState.postValue(State.Success("categories fetched"))
        _toastMessage.postValue("data fetched")
    }

    private fun onFailure(throwable: Throwable) {
        _fragmentState.postValue(State.Error("can not get categories"))
        _toastMessage.postValue("can not get categories")
    }


    companion object {
        private const val ELECTRONICS = 0
        private const val JEWELERY = 1
        private const val MENS = 2
        private const val WOMENS = 3
    }

    override fun onCategoryClicked() {

    }

}