package com.example.fakestoreapi.view.core

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    data class Error(val message: String) : State<Nothing>()
    object Loading : State<Nothing>()
    object NoState : State<Nothing>()

    fun toData(): T? {
        return if (this is Success) data else null
    }

    fun toErrorMessage(): String? {
        return if (this is Error) message else null
    }
}