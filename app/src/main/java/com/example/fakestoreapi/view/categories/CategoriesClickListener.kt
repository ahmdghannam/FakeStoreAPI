package com.example.fakestoreapi.view.categories

import com.example.fakestoreapi.view.base_classes.BaseRecyclerAdapter

interface CategoriesClickListener : BaseRecyclerAdapter.BaseInteractionListener {
    fun onCategoryClicked()
}