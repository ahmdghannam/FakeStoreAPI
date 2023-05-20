package com.example.fakestoreapi.view.categories

import com.example.fakestoreapi.model.dto.CartProduct

sealed class CartRecyclerItem {
    class TitleItem(val text: String) : CartRecyclerItem()
    class SubTitleItem(val text: String) : CartRecyclerItem()
    class ProductItem(val product: CartProduct) : CartRecyclerItem()

    fun toData():Any{
        if (this is TitleItem) return text
        else if (this is SubTitleItem) return text
        else if (this is ProductItem) return product.toString()
        else  return "aa"
    }
}
