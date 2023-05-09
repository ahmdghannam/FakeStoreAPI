package com.example.fakestoreapi.model.dto


import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("date")
    val date: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("products")
    val products: List<Product?>?,
    @SerializedName("userId")
    val userId: Int?,
)