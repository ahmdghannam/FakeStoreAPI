package com.example.fakestoreapi.view.home

import com.example.fakestoreapi.model.dto.ProductResponse

sealed class HomeRecyclerItem {
     class TitleItem(val text: String) : HomeRecyclerItem()
     class ProductItem(val product: ProductResponse) : HomeRecyclerItem()
}
