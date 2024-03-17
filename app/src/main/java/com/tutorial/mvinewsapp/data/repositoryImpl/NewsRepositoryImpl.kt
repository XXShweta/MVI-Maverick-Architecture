package com.tutorial.mvinewsapp.data.repositoryImpl

import com.tutorial.mvinewsapp.common.Resource
import com.tutorial.mvinewsapp.data.mapper.toNews
import com.tutorial.mvinewsapp.data.remote.NewsApi
import com.tutorial.mvinewsapp.domain.news.NewsArticle
import com.tutorial.mvinewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getNews(): Resource<List<NewsArticle>> {
        return try {
            Resource.Success(
                data = api.getNews().toNews()
            )
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message?: "Unknown Error")
        }
    }
}