package com.elnfach.arthouse.home

import com.elnfach.model.NewsItem

data class HomeState(
    val value: List<NewsItem> = emptyList(),
    val error: String = "",
    val loading: Boolean = false
)