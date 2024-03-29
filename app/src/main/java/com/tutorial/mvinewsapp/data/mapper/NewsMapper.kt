package com.tutorial.mvinewsapp.data.mapper

import com.tutorial.mvinewsapp.data.remote.NewsDto
import com.tutorial.mvinewsapp.domain.news.NewsArticle

fun NewsDto.toNews(): List<NewsArticle>{
    return articles.filter {
        it.image?.isNotEmpty() ?: false
    }.map {
        NewsArticle(
            it.image.toString(),
            it.title.toString(),
            it.description.toString()
        )
    }
}