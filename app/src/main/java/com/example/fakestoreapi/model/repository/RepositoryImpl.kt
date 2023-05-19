package com.example.fakestoreapi.model.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.fakestoreapi.model.api.API
import com.example.fakestoreapi.model.dto.CartResponse
import com.example.fakestoreapi.model.dto.LoginRequest
import com.example.fakestoreapi.model.dto.Name
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.model.dto.TokenResponse
import com.example.fakestoreapi.model.dto.UserDto
import com.example.fakestoreapi.model.local.SharedPreferencesUtil
import com.example.fakestoreapi.model.local.StoreDataBase
import com.example.fakestoreapi.model.local.entity.UserEntity
import com.example.fakestoreapi.utils.userNameToId
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Exception

class RepositoryImpl(
    private val sharedPreferences: SharedPreferencesUtil
) : Repository {

    private val apiService = API().fakeStoreApiService
    private val userDao = StoreDataBase.getInstance().userDao()

    private val compositeDisposable = CompositeDisposable()
    private fun Disposable.addToCompositeDisposable() {
        compositeDisposable.add(this)
    }
     override fun onCleared() {
        compositeDisposable.dispose()
    }


    override fun refreshUserData(): Completable {
        return Completable.create { emitter ->
            apiService.getUserById(getUserId()!!).subscribe(
                {
                    onSuccess(it)
                    emitter.onComplete()
                },
                {
                    onFailure(it)
                    emitter.onError(it)
                }
            ).addToCompositeDisposable()
        }
    }

    private fun onSuccess(it: UserDto) {
        val user = UserEntity(
            id = it.id,
            name = it.name.toString(),
            phone = it.phone ?: "",
            address = it.address.toString(),
            email = it.email.toString()
        )
        userDao.addTheUser(user).subscribe()
    }

    private fun onFailure(throwable: Throwable) {
        Log.i(TAG, "onFailure: no internet")
    }

    override fun getUserId(): Int? {
        return sharedPreferences.userId
    }

    override fun saveUserIdToSharedPreferences(username: String) {
        Log.i(TAG, "token : $username")
        val userId = userNameToId(username)
        Log.i(TAG, "user id : $userId")
        sharedPreferences.userId = userId
    }

    override fun logOut() {
        sharedPreferences.deleteLoginData()
    }

    override fun isLoggedIn(): Boolean {
        return sharedPreferences.isLoggedIn()
    }

    override fun loginWithUserNameAndPassword(loginRequest: LoginRequest): Single<TokenResponse> {
        return apiService.loginWithUserNameAndPasswordAndGetToken(loginRequest)
    }

    override fun getAllProducts(): Single<List<ProductResponse>> {
        return apiService.getAllProducts()
    }

    override fun getAllCategories(): Single<List<String>> {
        return apiService.getAllCategories()
    }

    override fun getCartsByUserId(userId: Int): Single<List<CartResponse>> {
        return apiService.getCartsByUserId(userId)
    }

    override fun getUser(): Single<UserEntity> {
        return userDao.getTheUser()
    }

    override fun getProductById(productId: Int): Single<ProductResponse> {
        return apiService.getProductById(productId)
    }

    companion object {
        private const val TAG = "Repository"
    }

}