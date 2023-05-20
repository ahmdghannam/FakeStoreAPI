package com.example.fakestoreapi.view.home

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.RecyclerItemHomeBinding
import com.example.fakestoreapi.databinding.RecyclerItemTitleBinding
import com.example.fakestoreapi.view.base_classes.BaseRecyclerAdapter

class HomeRecyclerAdapter(
    items: List<HomeRecyclerItem>,
    listener: HomeClickListeners
) : BaseRecyclerAdapter<HomeRecyclerItem>(items, listener) {
    override val layoutId = -1

    inner class ProductViewHolder(val binding: RecyclerItemHomeBinding) : BaseViewHolder(binding)
    inner class TitleViewHolder(val binding: RecyclerItemTitleBinding) : BaseViewHolder(binding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TITLE_ITEM -> TitleViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_title,
                    parent,
                    false
                )
            )

            PRODUCT_ITEM -> ProductViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_home,
                    parent,
                    false
                )
            )

            else -> throw Exception("item not found")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> bindTitle(holder, items[position])
            is ProductViewHolder -> bindProduct(holder, items[position])
            else -> throw Exception("item not found")
        }
    }

    private fun bindProduct(holder: ProductViewHolder, item: HomeRecyclerItem) {
        holder.binding.item = (item as HomeRecyclerItem.ProductItem).product
    }

    private fun bindTitle(holder: TitleViewHolder, item: HomeRecyclerItem) {
        holder.binding.item = (item as HomeRecyclerItem.TitleItem).text
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeRecyclerItem.TitleItem -> TITLE_ITEM
            is HomeRecyclerItem.ProductItem -> PRODUCT_ITEM
        }
    }


    companion object {
        private const val TITLE_ITEM = 12
        private const val PRODUCT_ITEM = 42
    }

}