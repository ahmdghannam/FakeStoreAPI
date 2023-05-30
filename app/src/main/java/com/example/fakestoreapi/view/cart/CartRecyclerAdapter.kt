package com.example.fakestoreapi.view.cart

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.RecyclerItemCartProductBinding
import com.example.fakestoreapi.databinding.RecyclerItemSubTitleBinding
import com.example.fakestoreapi.databinding.RecyclerItemTitleBinding
import com.example.fakestoreapi.model.dto.CartProduct
import com.example.fakestoreapi.model.dto.ProductResponse
import com.example.fakestoreapi.view.base_classes.BaseRecyclerAdapter
import java.lang.Exception

class CartRecyclerAdapter(
    items: List<CartRecyclerItem>, listener: CartClickListener
) : BaseRecyclerAdapter<CartRecyclerItem>(items, listener) {
    override val layoutId = -1

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

            SUB_TITLE_ITEM -> SubTitleViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_sub_title,
                    parent,
                    false
                )
            )

            PRODUCT_ITEM -> ProductViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_cart_product,
                    parent,
                    false
                )
            )

            else -> throw Exception("Item not found")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        when(holder){
            is TitleViewHolder -> bindTitle(holder, position)
            is SubTitleViewHolder -> bindSubTitle(holder, position)
            is ProductViewHolder -> bindProduct(holder, position)
        }
    }

    private fun bindProduct(holder: ProductViewHolder, position: Int) {
        holder.binding.item= items[position].toData() as CartProduct
    }

    private fun bindSubTitle(holder: SubTitleViewHolder, position: Int) {
        holder.binding.item = items[position].toData() as String
    }

    private fun bindTitle(holder: TitleViewHolder, position: Int) {
        holder.binding.item = items[position].toData() as String
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is CartRecyclerItem.ProductItem -> PRODUCT_ITEM
            is CartRecyclerItem.TitleItem -> TITLE_ITEM
            is CartRecyclerItem.SubTitleItem -> SUB_TITLE_ITEM
        }
    }

    inner class TitleViewHolder(val binding: RecyclerItemTitleBinding) :
        BaseViewHolder(binding)

    inner class SubTitleViewHolder(val binding: RecyclerItemSubTitleBinding) :
        BaseViewHolder(binding)

    inner class ProductViewHolder(val binding: RecyclerItemCartProductBinding) :
        BaseViewHolder(binding)

    companion object {
        private const val TITLE_ITEM = 12
        private const val SUB_TITLE_ITEM = 325
        private const val PRODUCT_ITEM = 122
    }
}