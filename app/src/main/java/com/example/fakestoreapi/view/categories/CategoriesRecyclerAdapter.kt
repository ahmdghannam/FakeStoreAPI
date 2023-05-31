package com.example.fakestoreapi.view.categories


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.RecyclerItemCategoriesBinding
import com.example.fakestoreapi.databinding.RecyclerItemTitleBinding
import com.example.fakestoreapi.model.dto.CategoryItem
import com.example.fakestoreapi.model.recyclerItems.RecyclerItem
import com.example.fakestoreapi.view.base_classes.BaseRecyclerAdapter

class CategoriesRecyclerAdapter(
    items: List<RecyclerItem<Any>>,
    listener: CategoriesClickListener
) : BaseRecyclerAdapter<RecyclerItem<Any>>(items, listener) {

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

            CATEGORY_ITEM -> CategoryItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.recycler_item_categories,
                    parent,
                    false
                )
            )

            else -> throw Exception("view type not found")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> bindTitle(holder, position)
            is CategoryItemViewHolder -> bindCategory(holder, position)
            else -> throw Exception("Ahmed: view holder not found")
        }
    }

    private fun bindTitle(viewHolder: TitleViewHolder, position: Int) {
        viewHolder.binding.item = items[position].item as String
    }

    private fun bindCategory(viewHolder: CategoryItemViewHolder, position: Int) {
        viewHolder.binding.item = items[position].item as CategoryItem
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].item) {
            is String -> TITLE_ITEM
            is CategoryItem -> CATEGORY_ITEM
            else -> throw Exception("Ahmed : Recycler Adapter not found")
        }
    }

    // view holders

    inner class TitleViewHolder(val binding: RecyclerItemTitleBinding) : BaseViewHolder(binding)
    inner class CategoryItemViewHolder(val binding: RecyclerItemCategoriesBinding) :
        BaseViewHolder(binding)

    private companion object {
        const val TITLE_ITEM = 12
        const val CATEGORY_ITEM = 13
    }
}