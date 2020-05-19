package com.books.models
import com.google.gson.annotations.SerializedName


data class BooksListResponseModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>
)

data class Result(
    @SerializedName("authors")
    val authors: List<Author>,
    @SerializedName("bookshelves")
    val bookshelves: List<String>,
    @SerializedName("download_count")
    val download_count: Int,
    @SerializedName("formats")
    val formats: Formats,
    @SerializedName("id")
    val id: Int,
    @SerializedName("languages")
    val languages: List<String>,
    @SerializedName("media_type")
    val media_type: String,
    @SerializedName("subjects")
    val subjects: List<String>,
    @SerializedName("title")
    val title: String
)

data class Author(
    @SerializedName("birth_year")
    val birth_year: Any,
    @SerializedName("death_year")
    val death_year: Any,
    @SerializedName("name")
    val name: String
)

data class Formats(
    @SerializedName("application/epub+zip")
    val applicationepubzip: String?,
    @SerializedName("application/pdf")
    val applicationpdf: String?,
    @SerializedName("application/rdf+xml")
    val applicationrdfxml: String,
    @SerializedName("application/x-mobipocket-ebook")
    val applicationxMobipocketEbook: String,
    @SerializedName("application/zip")
    val applicationzip: String?,
    @SerializedName("image/jpeg")
    val imagejpeg: String,
    @SerializedName("text/html")
    val texthtml: String,
    @SerializedName("text/html; charset=iso-8859-1")
    val texthtmlCharsetiso88591: String?,
    @SerializedName("text/html; charset=us-ascii")
    val texthtmlCharsetusAscii: String?,
    @SerializedName("text/html; charset=utf-8")
    val texthtmlCharsetutf8: String?,
    @SerializedName("text/plain")
    val textplain: String,
    @SerializedName("text/plain; charset=iso-8859-1")
    val textplainCharsetiso88591: String?,
    @SerializedName("text/plain; charset=us-ascii")
    val textplainCharsetusAscii: String?,
    @SerializedName("text/plain; charset=utf-8")
    val textplainCharsetutf8: String?,
    @SerializedName("text/rtf")
    val textrtf: String
)