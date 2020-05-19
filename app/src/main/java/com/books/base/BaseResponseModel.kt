package com.books.base
import com.google.gson.annotations.SerializedName


data class BaseResponseModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)