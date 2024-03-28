package com.elnfach.arthouse.create.newsItem

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.elnfach.domain.usecase.AddNewsArticleUseCase
import com.elnfach.model.NewsItem
import com.elnfach.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNewsItemScreenViewModel @Inject constructor(
    private val addNewsArticleUseCase: AddNewsArticleUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CreateNewsItemState())
    val state: State<CreateNewsItemState> = _state

    fun addNewsItem(newsItem: NewsItem, callback: (Boolean) -> Unit) {
        addNewsArticleUseCase(newsItem = newsItem, callback = { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CreateNewsItemState(value = result.data)
                    if(result.data != null)
                        callback(result.data!!)
                }
                is Resource.Error -> {
                    _state.value = CreateNewsItemState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CreateNewsItemState(loading = true)
                }
            }

        })
    }
}