package com.example.fakestoreapi.model.api

import com.example.fakestoreapi.model.dto.CartResponse
import com.example.fakestoreapi.model.dto.LoginRequest
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.model.dto.TokenResponse
import com.example.fakestoreapi.model.dto.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FakeStoreApiService {

    @POST("/auth/login")
    fun loginWithUserNameAndPasswordAndGetToken(
        @Body loginRequest: LoginRequest
    ): Single<TokenResponse>

    @GET("/products")
    fun getAllProducts(): Single<List<ProductResponse>>

    @GET("/products/categories")
    fun getAllCategories(): Single<List<String>>

    @GET("/carts/user/{userId}")
    fun getCartsByUserId(@Path("userId") userId: Int): Single<List<CartResponse>>

    @GET("/users/{userId}")
    fun getUserById(@Path("userId") userId: Int): Single<User>

    @GET("/products/{productId}")
    fun getProductById(@Path("productId") productID: Int): Single<ProductResponse>

}