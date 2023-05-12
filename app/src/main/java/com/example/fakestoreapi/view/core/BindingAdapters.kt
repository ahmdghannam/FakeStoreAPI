package com.example.fakestoreapi.view.core

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["app:hideWhenLoading"])
fun View.hideWhenLoading(state: State<String>?) {
    if (state == null) return

    visibility = if (state is State.Loading) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter(value = ["app:hideWhenSuccess"])
fun View.hideWhenSuccess(state: State<String>?) {
    if (state == null) return

    visibility = if (state is State.Success) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter(value = ["app:hideWhenError"])
fun View.hideWhenError(state: State<String>?) {
    if (state == null) return

    visibility = if (state is State.Error) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun View.showWhenLoading(state: State<String>?) {
    if (state == null) return

    visibility = if (state is State.Loading) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun View.showWhenSuccess(state: State<String>?) {
    if (state == null) return

    visibility = if (state is State.Success) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun View.showWhenError(state: State<String>?) {
    if (state == null) return

    visibility = if (state is State.Error) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}