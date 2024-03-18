package com.tutorial.mvinewsapp.presentation

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.tutorial.mvinewsapp.common.Resource
import com.tutorial.mvinewsapp.domain.repository.NewsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch


class NewsViewModel @AssistedInject constructor(
    private val newsRepository: NewsRepository,
    @Assisted state: NewsState
) : MavericksViewModel<NewsState>(state){

    init {
        loadNewsInfo()
    }

    private fun loadNewsInfo(){
        viewModelScope.launch {
            setState { copy(
                isLoading = true,
                error = null
            ) }

            when(val result = newsRepository.getNews()){
                is Resource.Success -> {
                    setState { copy(
                        newsInfo = result.data,
                        isLoading = false,
                        error = null
                    ) }

                }
                is Resource.Error -> {
                    setState { copy(
                        newsInfo = null,
                        isLoading = false,
                        error = result.message
                    ) }
                }
            }
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<NewsViewModel, NewsState> {
        override fun create(state: NewsState): NewsViewModel
    }

    companion object : MavericksViewModelFactory<NewsViewModel, NewsState> by hiltMavericksViewModelFactory()

}