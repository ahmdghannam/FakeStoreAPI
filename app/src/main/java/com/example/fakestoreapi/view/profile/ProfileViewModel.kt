package com.example.fakestoreapi.view.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.model.local.entity.UserEntity
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.model.repository.RepositoryImpl
import com.example.fakestoreapi.view.base_classes.BaseViewModel
import com.example.fakestoreapi.view.core.State
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel // this will create the factory and use it
class ProfileViewModel @Inject constructor(
    override val repository: Repository
) : BaseViewModel() {

    private val _user = MutableLiveData<UserEntity>()
    val user: LiveData<UserEntity>
        get() = _user

    init {
        Log.i("viewmodel", "view model started ")
        _fragmentState.postValue(State.Loading)
        repository
            .refreshUserData()
            .subscribe({
                getUserInformation()
            }, {
                getUserInformation()
            })
            .addToCompositeDisposable()
    }

    private fun getUserInformation() {
        repository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onFailure)
            .addToCompositeDisposable()
    }


    private fun onSuccess(userEntity: UserEntity) {
        Log.i("viewmodel", "onSuccess: user -> ${userEntity}")
        _user.postValue(userEntity)
        _fragmentState.postValue(State.Success("data fetched"))
    }

    private fun onFailure(throwable: Throwable) {
        Log.i("viewmodel", "onFailure : ${throwable.message.toString()}")
        _fragmentState.postValue(State.Error("user not found"))
        _toastMessage.postValue("user not found")
    }

    fun logOutUser() {
        repository.logOut()
        // todo go to login page
    }

    override fun onCleared() {
        super.onCleared()
        repository.onCleared()
    }

}