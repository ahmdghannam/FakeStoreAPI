package com.example.fakestoreapi.view.base_classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.view.core.State
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    abstract val repository: Repository
    protected val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    protected val _fragmentState = MutableLiveData<State<String>>(State.NoState)
    val fragmentState: LiveData<State<String>>
        get() = _fragmentState

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun Disposable.addToCompositeDisposable() {
        compositeDisposable.add(this)
    }


}