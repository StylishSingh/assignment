package com.books.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.books.R
import com.books.base.BaseViewHolder
import com.books.databinding.ListItemBooksBinding
import com.books.interfaces.OnItemClickListener
import com.books.models.Result
import com.books.utils.DiffUtilCallBack

class BooksListAdapter : PagedListAdapter<Result, BaseViewHolder<Result>>(DiffUtilCallBack()) {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Result> {
        val viewDataBinding: ListItemBooksBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_books,
                parent,
                false
            )

        return BooksGridHolder(viewDataBinding)
    }

    fun setItemSelectedListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Result>, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class BooksGridHolder(private var binding: ListItemBooksBinding) :
        BaseViewHolder<Result>(binding.root), View.OnClickListener {

        lateinit var holderListener: OnItemClickListener
        init {
            binding.ivCover.setOnClickListener(this)
        }


        private fun setItemSelectedListener(listener: OnItemClickListener) {
            this.holderListener = listener
        }

        override fun onClick(p0: View?) {
            getItem(adapterPosition)?.let { holderListener.onClick(it) }
        }

        override fun bind(item: Result) {
            binding.data = item
            setItemSelectedListener(listener)
        }

    }

}