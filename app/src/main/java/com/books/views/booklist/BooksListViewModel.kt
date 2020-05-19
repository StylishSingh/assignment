package com.books.views.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.books.base.BaseViewModel
import com.books.models.Result
import com.books.views.BooksDataFactory

class BooksListViewModel : BaseViewModel() {

    private var booksLiveData: LiveData<PagedList<Result>>? = null
    var progress = MutableLiveData<Boolean>()

    fun getBooks(
        query: String,
        resetData: Boolean = false
    ): LiveData<PagedList<Result>> {

        if (booksLiveData == null || resetData) {
            val dataSource = BooksDataFactory(query)
            val config = PagedList.Config.Builder()
                .setPageSize(30)
                .setInitialLoadSizeHint(30)
                .setEnablePlaceholders(false)
                .build()

            booksLiveData = LivePagedListBuilder(dataSource, config)
                .setInitialLoadKey("1")
                .build()
        }
        return booksLiveData as LiveData<PagedList<Result>>
    }

}
