package com.tutorial.mvinewsapp.data.remote

import com.squareup.moshi.Json

data class NewsDto(
    val articles: List<Article>
)
data class Article(
    val author: String?= null,
    val title: String?= null,
    val description: String?= null,
    @field:Json(name = "url")
    val articleUrl: String?= null,
    @field:Json(name = "urlToImage")
    val image: String?= null,
    @field:Json(name = "publishedAt")
    val date: String?= null,
    val content: String?= null
)
