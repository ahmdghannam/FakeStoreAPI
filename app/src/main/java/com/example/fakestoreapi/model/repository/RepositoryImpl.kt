package com.example.fakestoreapi.model.repository

import android.content.Context
import com.example.fakestoreapi.model.api.API
import com.example.fakestoreapi.model.api.FakeStoreApiService
import com.example.fakestoreapi.model.dto.CartResponse
import com.example.fakestoreapi.model.dto.LoginRequest
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.model.localdata.SharedPreferencesUtil
import com.example.fakestoreapi.utils.tokenToUserId
import io.reactivex.rxjava3.core.Single

class MyRepositoryImpl(
    private val apiService: FakeStoreApiService,
    private val sharedPreferences: SharedPreferencesUtil
) : MyRepository {

    override fun getUserId(token: String): Int? {
        return sharedPreferences.userId
    }

    override fun saveUserIdToSharedPreferences(token: String) {
        val userId = tokenToUserId(token)
        sharedPreferences.userId = userId
    }

    override fun logOut() {
        sharedPreferences.deleteLoginData()
    }

    override fun isLoggedIn(): Boolean {
        return sharedPreferences.isLoggedIn()
    }

    override fun loginWithUserNameAndPassword(loginRequest: LoginRequest): Single<String> {
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

    override fun getUserById(userId: Int): Single<ProductResponse> {
        return apiService.getUserById(userId)
    }

    override fun getProductById(productId: Int): Single<ProductResponse> {
        return apiService.getProductById(productId)
    }

}