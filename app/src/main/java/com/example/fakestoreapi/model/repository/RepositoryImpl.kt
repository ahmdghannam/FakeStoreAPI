package com.example.fakestoreapi.model.repository

import android.util.Log
import com.example.fakestoreapi.model.api.API
import com.example.fakestoreapi.model.dto.CartResponse
import com.example.fakestoreapi.model.dto.LoginRequest
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.model.dto.TokenResponse
import com.example.fakestoreapi.model.dto.User
import com.example.fakestoreapi.model.localdata.SharedPreferencesUtil
import com.example.fakestoreapi.utils.userNameToId
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val sharedPreferences: SharedPreferencesUtil
) : Repository {

    private val apiService = API().fakeStoreApiService

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

    override fun getUser(): Single<User> {
        getUserId()?.let {
            return apiService.getUserById(it)
        }
        throw Exception("user not login")
    }

    override fun getProductById(productId: Int): Single<ProductResponse> {
        return apiService.getProductById(productId)
    }

    companion object{
        private const val TAG="Repository"
    }

}