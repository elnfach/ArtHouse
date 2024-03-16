package com.elnfach.arthouse.presentation.foryou

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elnfach.arthouse.domain.usecase.GetNewsArticleByIdUseCase
import com.elnfach.arthouse.domain.usecase.GetNewsArticlesUseCase
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
        loadNewsArticles()
        Log.e("TESTING | VIEWMODEL", state.value.isLoading.toString())
    }

    private fun loadNewsArticles() {
        getNewsArticlesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = ForYouState(newsArticles = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = ForYouState(isLoading = true)
                    Log.e("TESTING | LOADING", _state.value.isLoading.toString())
                }
                is Resource.Error -> {
                    _state.value = ForYouState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNewsArticleById(id:String)
    {
        getNewsArticleByIdUseCase(id).onEach {result->
            when(result){
                is Resource.Success -> {
                    _newsArticlePageState.value = NewsArticlePageState(newsArticle = result.data ?: NewsArticle())
                }
                is Resource.Loading -> {
                    _newsArticlePageState.value = NewsArticlePageState(isLoading = true)
                    Log.e("TESTING | LOADING", _state.value.isLoading.toString())
                }
                is Resource.Error -> {
                    _newsArticlePageState.value = NewsArticlePageState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun pullToRefresh()
    {
        loadNewsArticles()
    }
}