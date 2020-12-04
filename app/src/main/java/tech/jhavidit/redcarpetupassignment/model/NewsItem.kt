package tech.jhavidit.redcarpetupassignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
    val articles: List<Article>? = null,
    val status: String?=null,
    val totalResults:Int?=null
) : Parcelable

@Parcelize
data class Source(
    val id: String? = null,
    val name: String?=null
) : Parcelable

@Parcelize
data class Article(
    val author: String? = null,
    val content: String? = null,
    val description: String?=null ,
    val publishedAt: String?=null,
    val source: Source? = null,
    val title: String?=null,
    val url: String?=null,
    val urlToImage: String?=null
) : Parcelable