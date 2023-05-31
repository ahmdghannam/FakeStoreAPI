package com.example.fakestoreapi.view.home

import com.example.fakestoreapi.model.dto.ProductResponse

sealed class HomeRecyclerItem {
     class TitleItem(val text: String) : HomeRecyclerItem()
     class ProductItem(val product: ProductResponse) : HomeRecyclerItem()

     fun toData():String{
          if (this is TitleItem)
               return text
          else if(this is ProductItem) return product.toString()
          else return ""
     }
}
