package com.example.fakestoreapi.model

import android.content.Context
import com.example.fakestoreapi.model.api.API
import com.example.fakestoreapi.model.dto.CartResponse
import com.example.fakestoreapi.model.dto.LoginRequest
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.model.localdata.SharedPreferencesUtil
import com.example.fakestoreapi.utils.tokenToUserId
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Path

class repository(applicationContext: Context) {

    private val apiService = API().fakeStoreApiService
    private val sharedPreferences = SharedPreferencesUtil(applicationContext)

    fun getUserId(token: String): Int? {
        return sharedPreferences.userId
    }

    fun saveUserIdToSharedPreferences(token: String) {
        val userId = tokenToUserId(token)
        sharedPreferences.userId = userId
    }

    fun logOut() {
        sharedPreferences.deleteLoginData()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.isLoggedIn()
    }

    fun loginWithUserNameAndPassword(loginRequest: LoginRequest): Single<String> {
        return apiService.loginWithUserNameAndPasswordAndGetToken(loginRequest)
    }

    fun getAllProducts(): Single<List<ProductResponse>> {
        return apiService.getAllProducts()
    }

    fun getAllCategories(): Single<List<String>> {
        return apiService.getAllCategories()
    }

    fun getCartsByUserId(userId: Int): Single<List<CartResponse>> {
        return apiService.getCartsByUserId(userId)
    }

    fun getUserById(userId: Int): Single<ProductResponse> {
        return apiService.getUserById(userId)
    }

    fun getProductById(productID: Int): Single<ProductResponse> {
        return apiService.getProductById(productID)
    }


}