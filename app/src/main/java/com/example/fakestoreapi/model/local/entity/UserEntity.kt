package com.example.fakestoreapi.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fakestoreapi.model.dto.Address
import com.example.fakestoreapi.model.dto.Name

@Entity("TABLE_USER")
data class UserEntity(
    @PrimaryKey
    val id:Int?,
    val name: String,
    val address: String?,
    val phone: String,
    val email:String
){
    fun toEmail(): String {
        return "email: $email"
    }

    fun toPhone(): String {
        return "phone: $phone"
    }
}