package com.darksoft.arquitecturamvvm.base

import androidx.recyclerview.widget.RecyclerView
import com.darksoft.arquitecturamvvm.databinding.ItemRowBinding

abstract class BaseViewHolder<T>(itemView: ItemRowBinding): RecyclerView.ViewHolder(itemView.root){
    abstract fun bind(item: T, position: Int)
}