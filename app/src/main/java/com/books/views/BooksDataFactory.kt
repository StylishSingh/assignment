package com.books.views

import androidx.paging.DataSource
import com.books.models.Result
import kotlinx.coroutines.Dispatchers

class BooksDataFactory(private var text:String): DataSource.Factory<String, Result>() {
    override fun create(): DataSource<String, Result> {
        return BooksDataSource(Dispatchers.IO,text)
    }
}