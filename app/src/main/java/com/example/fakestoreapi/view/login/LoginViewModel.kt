package com.example.fakestoreapi.view.login

import androidx.lifecycle.MutableLiveData
import com.example.fakestoreapi.model.dto.LoginRequest
import com.example.fakestoreapi.model.dto.TokenResponse
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.model.repository.Repository
import com.example.fakestoreapi.model.repository.RepositoryImpl
import com.example.fakestoreapi.view.core.State
import com.example.fakestoreapi.view.base_classes.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    override val repository: Repository
) : BaseViewModel() {

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun loginUser() {
        if (isValidInput()) {
            subscribeToRepositorySingle()
        } else {
            _toastMessage.postValue("invalid input")
        }
    }

    private fun isValidInput(): Boolean {
        return userName.value?.trim()?.isNotEmpty() == true &&
                password.value?.trim()?.isNotEmpty() == true
    }

    private fun subscribeToRepositorySingle() {
        _fragmentState.postValue(State.Loading)
        val loginRequest = LoginRequest(userName.value!!, password.value!!)
        repository.loginWithUserNameAndPassword(loginRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onFailure)
            .addToCompositeDisposable()
    }

    private fun onSuccess(tokenResponse: TokenResponse) {
        userName.value?.let {
            _fragmentState.postValue(State.Success("login Successfully"))
            repository.saveUserIdToSharedPreferences(it)
        }
    }

    private fun onFailure(throwable: Throwable) {
        _fragmentState.postValue(State.Error("error while login"))
    }

}



