package com.tutorial.mvinewsapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorial.mvinewsapp.common.Resource
import com.tutorial.mvinewsapp.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel(){

    var state by mutableStateOf(NewsState())
        private set

    init {
        loadNewsInfo()
    }

    private fun loadNewsInfo(){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when(val result = newsRepository.getNews()){
                is Resource.Success -> {
                    state = state.copy(
                       newsInfo = result.data,
                       isLoading = false,
                       error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        newsInfo = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}