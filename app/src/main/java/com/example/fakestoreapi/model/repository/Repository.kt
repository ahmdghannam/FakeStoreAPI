package com.example.fakestoreapi.model.repository

import com.example.fakestoreapi.model.dto.CartResponse
import com.example.fakestoreapi.model.dto.LoginRequest
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.model.dto.TokenResponse
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getUserId(token: String): Int?
    fun saveUserIdToSharedPreferences(token: String)
    fun logOut()
    fun isLoggedIn(): Boolean
    fun loginWithUserNameAndPassword(loginRequest: LoginRequest): Single<TokenResponse>
    fun getAllProducts(): Single<List<ProductResponse>>
    fun getAllCategories(): Single<List<String>>
    fun getCartsByUserId(userId: Int): Single<List<CartResponse>>
    fun getUserById(userId: Int): Single<ProductResponse>
    fun getProductById(productId: Int): Single<ProductResponse>
}