package com.example.fakestoreapi.model.repository

import com.example.fakestoreapi.model.dto.CartResponse
import com.example.fakestoreapi.model.dto.LoginRequest
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.model.dto.TokenResponse
import com.example.fakestoreapi.model.local.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun refreshUserData(): Completable
    fun getUserId(): Int?
    fun saveUserIdToSharedPreferences(token: String)
    fun logOut()
    fun isLoggedIn(): Boolean
    fun loginWithUserNameAndPassword(loginRequest: LoginRequest): Single<TokenResponse>
    fun getAllProducts(): Single<List<ProductResponse>>
    fun getAllCategories(): Single<List<String>>
    fun getAllCarts(): Single<List<CartResponse>>
    fun getUser(): Single<UserEntity>
    fun getProductById(productId: Int): Single<ProductResponse>
    fun onCleared()
}