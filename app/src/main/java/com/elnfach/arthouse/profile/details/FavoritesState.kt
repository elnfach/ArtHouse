package com.elnfach.arthouse.profile.details

import com.elnfach.model.NewsItem

data class FavoritesState(
    val news: List<NewsItem>? = null,
    val error: String = "",
    val loading: Boolean = false
)
