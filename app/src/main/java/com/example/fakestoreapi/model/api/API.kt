package com.example.fakestoreapi.model.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private val retrofit=Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val fakeStoreApiService = retrofit.create(FakeStoreApiService::class.java)

    private companion object {
         const val BASE_URL = "https://fakestoreapi.com"
    }
}