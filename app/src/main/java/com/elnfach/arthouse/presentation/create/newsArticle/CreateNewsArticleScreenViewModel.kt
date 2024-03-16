package com.elnfach.arthouse.presentation.create.newsArticle

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elnfach.arthouse.domain.usecase.AddNewsArticleUseCase
import com.elnfach.arthouse.domain.usecase.GetNewsArticlesUseCase
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.foryou.ForYouState
import com.elnfach.arthouse.presentation.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CreateNewsArticleScreenViewModel(
    private val addNewsArticleUseCase: AddNewsArticleUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CreateNewsArticleState())
    val state: State<CreateNewsArticleState> = _state

    init {
        Log.e("TESTING | VIEWMODEL", state.value.isLoading.toString())
        Log.e("TESTING | VIEWMODEL", state.value.isLoading.toString())
    }

    private fun privateAddNewsArticles(newsArticle: NewsArticle) {
        addNewsArticleUseCase(newsArticle).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CreateNewsArticleState(success = result.data ?: true)
                }
                is Resource.Loading -> {
                    _state.value = CreateNewsArticleState(isLoading = true)
                    Log.e("TESTING | LOADING", _state.value.isLoading.toString())
                }
                is Resource.Error -> {
                    _state.value = CreateNewsArticleState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addNewsArticles(newsArticle: NewsArticle)
    {
        privateAddNewsArticles(newsArticle)
    }
}