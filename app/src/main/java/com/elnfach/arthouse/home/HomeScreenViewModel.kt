package com.elnfach.arthouse.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.home.details.NewsDetailsState
import com.elnfach.domain.usecase.AddNewsItemToFavoritesUseCase
import com.elnfach.domain.usecase.GetNewsByOrderUseCase
import com.elnfach.domain.usecase.GetNewsItemByIdUseCase
import com.elnfach.domain.usecase.IsAuthedUserUseCase
import com.elnfach.model.NewsItem
import com.elnfach.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getNewsByOrderUseCase: GetNewsByOrderUseCase,
    private val getNewsItemByIdUseCase: GetNewsItemByIdUseCase,
    private val getIsAuthedUserUseCase: IsAuthedUserUseCase,
    private val addNewsItemToFavoritesUseCase: AddNewsItemToFavoritesUseCase
) : ViewModel() {
    private val _homeStateMutable = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeStateMutable

    private val _newsDetailsState = mutableStateOf(NewsDetailsState())
    val newsDetailsState: State<NewsDetailsState> = _newsDetailsState

    private val _filter = mutableStateOf(HomeFilterState())
    private val _filterByPublished = _filter.value.value

    init {
        loadNewsByOrder(_filterByPublished)
    }

    private fun loadNewsByOrder(order: String) {
        getNewsByOrderUseCase(order, callback = { result ->
            when(result){
                is Resource.Success -> {
                    _homeStateMutable.value = HomeState(value = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _homeStateMutable.value = HomeState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _homeStateMutable.value = HomeState(loading = true)
                }
            }
        })
    }

    fun loadNewsItemById(id:String)
    {
        getNewsItemByIdUseCase(id, callback = { result->
            when(result){
                is Resource.Success -> {
                    _newsDetailsState.value = NewsDetailsState(value = result.data ?: NewsItem())
                }
                is Resource.Error -> {
                    _newsDetailsState.value = NewsDetailsState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _newsDetailsState.value = NewsDetailsState(loading = true)
                }
            }
        })
    }

    fun pullToRefresh()
    {
        loadNewsByOrder(_filterByPublished)
    }

    fun addNewItemToFavorites(id: String, callback: (Resource<Boolean>) -> Unit)
    {
        addNewsItemToFavoritesUseCase.invoke(id, callback)
    }
}