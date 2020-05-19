package com.books.networks

import com.books.models.BooksListResponseModel
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @GET(APIEndPoints.BOOKS)
    suspend fun getBooks(
        @Query("mime_type") page: String,
        @Query("search") mime_type: String,
        @Query("page") search: String
    ): Response<BooksListResponseModel>


}