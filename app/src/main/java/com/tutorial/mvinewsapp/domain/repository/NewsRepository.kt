package com.tutorial.mvinewsapp.domain.repository

import com.tutorial.mvinewsapp.common.Resource
import com.tutorial.mvinewsapp.domain.news.NewsArticle

interface NewsRepository {
    suspend fun getNews(): Resource<List<NewsArticle>>
}