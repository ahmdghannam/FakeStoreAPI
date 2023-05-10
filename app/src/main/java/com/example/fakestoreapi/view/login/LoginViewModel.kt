package com.example.fakestoreapi.view.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.model.dto.LoginRequest
import com.example.fakestoreapi.model.localdata.SharedPreferencesUtil
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.model.repository.RepositoryImpl
import com.example.fakestoreapi.view.base_classes.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.math.log

class LoginViewModel(
    sharedPrefsUtil: SharedPreferencesUtil
) : BaseViewModel() {
    private val repository: Repository = RepositoryImpl(sharedPrefsUtil)

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()


    private fun isValidInput(): Boolean {
        return userName.value?.trim()?.isNotEmpty() == true &&
                password.value?.trim()?.isNotEmpty() == true
    }

    fun loginUser() {
        Log.i(TAG, "enter")
        if (isValidInput()) {
            val loginRequest = LoginRequest(userName.value!!, password.value!!)
            repository.loginWithUserNameAndPassword(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i(TAG, "user token: ${it.token} ")
                },{
                    Log.i(TAG, it.message.toString())
                })
                .addToCompositeDisposable()
        } else {
            Log.i(TAG, "is not logged in")
        }
    }


    companion object {
        private const val TAG = "LoginViewModel"
    }

}

class ViewModelFactory(private val sharedPreferencesUtil: SharedPreferencesUtil) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(sharedPreferencesUtil) as T
        } else {
            throw Exception("view model not found")
        }
    }
}

