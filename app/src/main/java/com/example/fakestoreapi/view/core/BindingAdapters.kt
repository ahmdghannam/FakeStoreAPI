package com.example.fakestoreapi.view.core

import android.view.View
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestoreapi.R
import com.example.fakestoreapi.view.base_classes.BaseRecyclerAdapter

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

@BindingAdapter(value = ["app:setImageWithUrl"])
fun ImageView.setImageWithUrl(url: String?) {
    Glide.with(this)
        .load(url)
        .error(Glide.with(this).load(R.drawable.ic_iamge_notfound).centerCrop().into(this))
        .into(this)
}

@BindingAdapter(value = ["app:setRatingValue"])
fun RatingBar.setRatingValue(r: Double?) {
    if (r == null) return
    val rating = if (r > 5) 5 else r
    this.rating = rating.toFloat()
}

@BindingAdapter(value = ["app:setItemsList"])
fun <T> RecyclerView.setItemsList(list: List<T>?) {
    (adapter as BaseRecyclerAdapter<T>).setItemsList(list ?: mutableListOf())
}

@BindingAdapter(value = ["app:setImageDrawableSrc"])
fun ImageView.setImageDrawableSrc(src:Int){
    this.setImageResource(src)
}