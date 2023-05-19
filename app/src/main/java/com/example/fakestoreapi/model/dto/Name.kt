package com.example.fakestoreapi.model.dto


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("lastname")
    val lastname: String?
){
    override fun toString(): String {
        return "$firstname $lastname"
    }
}