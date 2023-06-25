package com.elnfach.arthouse.presentation.foryou

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elnfach.arthouse.domain.usecase.GetNewsArticlesUseCase
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ForYouScreenViewModel(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ForYouState())
    val state: State<ForYouState> = _state

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
}