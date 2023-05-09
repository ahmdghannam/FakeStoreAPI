package com.example.fakestoreapi.model.dto


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: Name?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("username")
    val username: String?,
)