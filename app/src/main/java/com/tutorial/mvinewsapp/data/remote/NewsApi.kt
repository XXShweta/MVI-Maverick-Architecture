package com.tutorial.mvinewsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") topic : String = "City",
        @Query("apiKey") apiKey: String = ""
    ): NewsDto
}