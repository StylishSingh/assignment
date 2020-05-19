package com.books.interfaces


interface OnItemClickListener {

    fun onClick(position: Int, type: String)
    fun onClick(data: Any)

}