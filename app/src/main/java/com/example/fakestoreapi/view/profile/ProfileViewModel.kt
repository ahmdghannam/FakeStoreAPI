package com.example.fakestoreapi.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestoreapi.model.dto.User
import com.example.fakestoreapi.model.localdata.SharedPreferencesUtil
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.model.repository.RepositoryImpl
import com.example.fakestoreapi.view.base_classes.BaseViewModel
import com.example.fakestoreapi.view.core.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfileViewModel(
    sharedPreferences: SharedPreferencesUtil
) : BaseViewModel() {

    override val repository: Repository = RepositoryImpl(sharedPreferences)
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String>
        get() = _phone

    private val _address = MutableLiveData<String>()
    val address: LiveData<String>
        get() = _address

    init {
        getUserInformation()
    }

    private fun getUserInformation(){
        _fragmentState.postValue(State.Loading)
        repository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess,::onFailure)
            .addToCompositeDisposable()
    }


    private fun onSuccess(user: User){
        user.apply {
            username?.let {
                _name.postValue(it)
            }
            email?.let {
                _email.postValue(toEmail())
            }
            address?.let {
                _email.postValue(it.toString())
            }
            phone?.let {
                _phone.postValue(toPhone())
            }
        }
        _fragmentState.postValue(State.Success("data fetched"))
    }

    private fun onFailure(throwable: Throwable){
        _fragmentState.postValue(State.Error("user not found"))
        _toastMessage.postValue("user not found")
    }

    fun logOutUser(){
        repository.logOut()
        // todo go to login page
    }

}