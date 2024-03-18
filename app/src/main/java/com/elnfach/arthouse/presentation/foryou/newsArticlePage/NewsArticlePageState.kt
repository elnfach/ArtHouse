package com.elnfach.arthouse.presentation.foryou.newsArticlePage

import com.elnfach.arthouse.model.NewsArticle

data class NewsArticlePageState(
    val isLoading: Boolean = false,
    val newsArticle: NewsArticle? = null,
    val error: String = ""
)