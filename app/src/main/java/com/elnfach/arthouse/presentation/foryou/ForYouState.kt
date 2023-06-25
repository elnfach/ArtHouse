package com.elnfach.arthouse.presentation.foryou

import com.elnfach.arthouse.model.NewsArticle

data class ForYouState(
    val isLoading: Boolean = false,
    val newsArticles: List<NewsArticle?> = emptyList(),
    val error: String = ""
)