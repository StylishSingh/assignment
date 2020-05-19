package com.books.utils

import androidx.recyclerview.widget.DiffUtil
import com.books.models.Result

class DiffUtilCallBack : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.title == newItem.title
                && oldItem.authors == newItem.authors
    }

}