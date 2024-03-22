package com.elnfach.arthouse.presentation.foryou.newsArticlePage

import com.elnfach.model.NewsArticle

data class NewsArticlePageState(
    val isLoading: Boolean = false,
    val newsArticle: com.elnfach.model.NewsArticle? = null,
    val error: String = ""
)