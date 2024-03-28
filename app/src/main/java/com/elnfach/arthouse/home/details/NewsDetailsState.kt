package com.elnfach.arthouse.home.details

import com.elnfach.model.NewsItem

data class NewsDetailsState(
    val value: NewsItem = NewsItem(),
    val error: String = "",
    val loading: Boolean = false
)