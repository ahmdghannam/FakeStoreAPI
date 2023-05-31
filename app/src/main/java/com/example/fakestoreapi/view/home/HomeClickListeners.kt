package com.example.fakestoreapi.view.home

import com.example.fakestoreapi.view.base_classes.BaseRecyclerAdapter

interface HomeClickListeners : BaseRecyclerAdapter.BaseInteractionListener {
    fun onProductClickListener()
}