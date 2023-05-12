package com.example.fakestoreapi.utils

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.fakestoreapi.R

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