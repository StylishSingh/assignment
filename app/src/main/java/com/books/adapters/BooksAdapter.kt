package com.books.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.books.R
import com.books.base.BaseViewHolder
import com.books.databinding.ListItemBookTypeBinding
import com.books.databinding.ListItemBooksBinding
import com.books.interfaces.OnItemClickListener
import com.books.models.BooksListResponseModel
import com.books.models.BooksType
import com.books.models.Result


class BooksAdapter(list: List<Any>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    lateinit var listener: OnItemClickListener
    private var adapterDataList: List<Any> = list

    init {
        setHasStableIds(true)
    }


    companion object {
        private const val TYPE_BOOKS = 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = adapterDataList[position]
        when (holder) {
            is BooksTypeHolder -> holder.bind(element as BooksType)
            else -> throw IllegalArgumentException()
        }

    }

    fun setItemSelectedListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        return when (viewType) {
            TYPE_BOOKS -> {
                val viewDataBinding: ListItemBookTypeBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.list_item_book_type,
                        parent,
                        false
                    )

                BooksTypeHolder(viewDataBinding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return adapterDataList.size
    }

    fun updateList(it: List<Any>) {
        adapterDataList = it
        notifyDataSetChanged()
    }

    fun updateItem(it: List<Any>, position: Int) {
        adapterDataList = it
        notifyItemChanged(position)
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterDataList[position]) {
            is BooksType -> {
                TYPE_BOOKS
            }
            else -> {
                TYPE_BOOKS
            }
        }

    }

    inner class BooksTypeHolder(private var binding: ListItemBookTypeBinding) :
        BaseViewHolder<BooksType>(binding.root), View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

        private lateinit var holderListener: OnItemClickListener

        init {
            binding.tvDeviceName.setOnClickListener(this)
            binding.ivType.setOnClickListener(this)
        }


        private fun setItemSelectedListener(listener: OnItemClickListener) {
            this.holderListener = listener
        }

        override fun onClick(p0: View?) {
            holderListener.onClick(adapterPosition,"")
        }

        override fun bind(item: BooksType) {
            binding.data = item
            setItemSelectedListener(listener)
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        }
    }


}