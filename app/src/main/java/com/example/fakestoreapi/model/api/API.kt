package com.example.fakestoreapi.model.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class API {

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    val client = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .callTimeout(30, TimeUnit.SECONDS)
        .build()
    private val retrofit = Retrofit
        .Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val fakeStoreApiService = retrofit.create(FakeStoreApiService::class.java)

    private companion object {
        const val BASE_URL = "https://fakestoreapi.com"
    }
}