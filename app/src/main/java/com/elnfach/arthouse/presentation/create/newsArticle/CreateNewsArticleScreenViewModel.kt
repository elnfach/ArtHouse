package com.elnfach.arthouse.presentation.create.newsArticle

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.elnfach.domain.usecase.AddNewsArticleUseCase
import com.elnfach.model.NewsArticle
import com.elnfach.model.Resource

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
        addNewsArticleUseCase(newsArticle = newsArticle, callback = { result ->
            when(result){
                is Resource.Push -> {
                    _state.value = CreateNewsArticleState(success = result.data ?: true)
                }
                is Resource.Error -> {
                    _state.value = CreateNewsArticleState(error = result.message ?: "An unexpected error occurred")
                }
            }

        })
    }

    fun addNewsArticles(newsArticle: NewsArticle)
    {
        privateAddNewsArticles(newsArticle)
    }
}