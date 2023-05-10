package com.example.fakestoreapi.model.dto


import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token")
    val token: String?
)