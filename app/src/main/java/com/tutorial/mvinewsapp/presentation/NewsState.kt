package com.tutorial.mvinewsapp.presentation

import com.airbnb.mvrx.MavericksState
import com.tutorial.mvinewsapp.domain.news.NewsArticle

data class NewsState(
    val newsInfo: List<NewsArticle>?= null,
    val isLoading: Boolean = false,
    val error: String?= null
): MavericksState
