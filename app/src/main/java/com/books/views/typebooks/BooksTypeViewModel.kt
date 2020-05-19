package com.books.views.typebooks

import com.books.base.BaseViewModel
import com.books.models.BooksType

class BooksTypeViewModel : BaseViewModel() {


    var list = mutableListOf<BooksType>()


    fun addDataToList() {
        list.add(BooksType( "Fiction", "ic_fiction"))
        list.add(BooksType( "Drama", "ic_drama"))
        list.add(BooksType( "Humor", "ic_humour"))
        list.add(BooksType( "Politics", "ic_politics"))
        list.add(BooksType( "Philosophy", "ic_philosophy"))
        list.add(BooksType( "History", "ic_history"))
        list.add(BooksType( "Adventure", "ic_adventure"))
    }
}
