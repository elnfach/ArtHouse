package com.elnfach.arthouse.presentation.create.newsArticle

import com.elnfach.arthouse.model.NewsArticle

data class CreateNewsArticleState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)
