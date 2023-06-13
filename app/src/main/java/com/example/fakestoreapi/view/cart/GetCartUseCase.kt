package com.example.fakestoreapi.view.cart

import com.example.fakestoreapi.model.dto.CartResponse
import com.example.fakestoreapi.model.repository.Repository

class GetCartUseCase {
    private val repository: Repository? = null
    private val mapper = null

    suspend operator fun invoke(location: Location): List<CartResponse>? {
        return repository
            ?.getAllCarts()
            ?.blockingGet()
            ?.sortedBy {
                it.date
            }?.reversed()
    }

    private fun doSomething() {

    }

    data class Location(
        val latitude: Double,
        val longitude: Double
    )
}