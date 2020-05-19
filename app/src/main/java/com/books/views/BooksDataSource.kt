package com.books.views

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.books.models.Result
import com.books.networks.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BooksDataSource(coroutineContext: CoroutineContext, private var query: String) :
    PageKeyedDataSource<String, Result>() {
    private val service = RetrofitFactory.retrofitService()
    private var currentPage: String? = "1"
    private var nextPage: String? = "1"
    private var previousPage: String? = ""
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)


    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Result>
    ) {
        scope.launch {
            try {
                currentPage = nextPage
                val response = service.getBooks("image/jpeg", query, currentPage!!)
                when {
                    response.isSuccessful -> {
                        val data = response.body()
                        val listing = response.body()?.results

                        nextPage = if (data!!.next != null)
                            data.next!!.substringAfter("page=").substringBefore("&search")
                        else
                            null

                        previousPage = if (data.previous != null)
                            data.previous.substringAfter("page=").substringBefore("&search")
                        else
                            null

                        callback.onResult(
                            listing ?: listOf(),
                            previousPage, nextPage
                        )
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Result>) {
        scope.launch {
            try {
                currentPage = nextPage
                val response = service.getBooks("image/jpeg", query, currentPage!!)
                when {
                    response.isSuccessful -> {
                        val data = response.body()
                        val listing = response.body()?.results

                        nextPage = if (data!!.next != null)
                            data.next!!.substringAfter("page=").substringBefore("&search")
                        else
                            null

                        previousPage = if (data!!.next != null)
                            data.previous!!.substringAfter("page=").substringBefore("&search")
                        else
                            null

                        callback.onResult(listing ?: listOf(), nextPage)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Result>) {
        scope.launch {
            try {
                currentPage = nextPage
                val response = service.getBooks("image/jpeg", query, currentPage!!)
                when {
                    response.isSuccessful -> {
                        val data = response.body()
                        val listing = response.body()?.results

                        nextPage = if (data!!.next != null)
                            data.next!!.substringAfter("page=").substringBefore("&search")
                        else
                            null

                        previousPage = if (data!!.next != null)
                            data.previous!!.substringAfter("page=").substringBefore("&search")
                        else
                            null

                        callback.onResult(listing ?: listOf(), previousPage)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

}