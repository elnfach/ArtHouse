package com.elnfach.arthouse.presentation.foryou

import com.elnfach.model.NewsArticle

data class ForYouState(
    val isLoading: Boolean = false,
    val newsArticles: List<com.elnfach.model.NewsArticle> = emptyList(),
    val error: String = ""
)