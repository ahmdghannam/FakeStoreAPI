package com.example.fakestoreapi.view.cart

import com.example.fakestoreapi.view.base_classes.BaseRecyclerAdapter

interface CartClickListener :BaseRecyclerAdapter.BaseInteractionListener {
    fun onProductClickListener()
}