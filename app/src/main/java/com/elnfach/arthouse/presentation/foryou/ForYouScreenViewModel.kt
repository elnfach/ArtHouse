package com.elnfach.arthouse.presentation.foryou

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.presentation.foryou.newsArticlePage.NewsArticlePageState
import com.elnfach.domain.usecase.GetNewsArticleByIdUseCase
import com.elnfach.domain.usecase.GetNewsArticlesUseCase
import com.elnfach.model.NewsArticle
import com.elnfach.model.Resource

class ForYouScreenViewModel(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase,
    private val getNewsArticleByIdUseCase: GetNewsArticleByIdUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ForYouState())
    val state: State<ForYouState> = _state

    private val _newsArticlePageState = mutableStateOf(NewsArticlePageState())
    val newsArticlePageState: State<NewsArticlePageState> = _newsArticlePageState

    init {
        Log.e("TESTING | VIEWMODEL", state.value.isLoading.toString())
        //loadNewsArticles()
        Log.e("TESTING | VIEWMODEL", state.value.isLoading.toString())
    }

    private fun loadNewsArticles() {
        getNewsArticlesUseCase(callback = { result ->
            when(result){
                is Resource.Push -> {
                    _state.value = ForYouState(newsArticles = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ForYouState(error = result.message ?: "An unexpected error occurred")
                }
            }
        })
    }

    fun loadNewsArticleById(id:String)
    {
        getNewsArticleByIdUseCase(id, callback = { result->
            when(result){
                is Resource.Push -> {
                    _newsArticlePageState.value = NewsArticlePageState(newsArticle = result.data ?: NewsArticle())
                }
                is Resource.Error -> {
                    _newsArticlePageState.value = NewsArticlePageState(error = result.message ?: "An unexpected error occurred")
                }
            }
        })
    }

    fun pullToRefresh()
    {
        loadNewsArticles()
    }
}