package com.elnfach.arthouse.presentation.create.newsArticle

data class CreateNewsArticleState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)
