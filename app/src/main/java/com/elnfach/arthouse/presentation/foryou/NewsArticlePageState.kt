package com.elnfach.arthouse.presentation.foryou

import com.elnfach.arthouse.model.NewsArticle

data class NewsArticlePageState(
    val isLoading: Boolean = false,
    val newsArticle: NewsArticle? = NewsArticle(),
    val error: String = ""
)