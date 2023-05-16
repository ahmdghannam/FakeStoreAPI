package com.example.fakestoreapi.view.base_classes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestoreapi.BR

abstract class BaseRecyclerAdapter<T>(
    protected var items: List<T>,
    protected var listener: BaseInteractionListener
) : RecyclerView.Adapter<BaseRecyclerAdapter<T>.BaseViewHolder>() {

    abstract val layoutId: Int

    interface BaseInteractionListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return OneItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is OneItemViewHolder) {
            holder.binding.setVariable(BR.item, items[position])
            holder.binding.setVariable(BR.listener, listener)
        }
    }

    fun getItemsList() = items
    fun setItemsList(newItems: List<T>) {
        val diffUtil = DiffUtil.calculateDiff(MyDiffUtils(items, newItems))
        items = newItems
        diffUtil.dispatchUpdatesTo(this)
    }

    inner class OneItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    abstract inner class BaseViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class MyDiffUtils<T>(private val oldList: List<T>, private val newList: List<T>) :
        DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}