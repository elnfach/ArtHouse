package com.elnfach.model

data class NewsArticle(
    val image: String = "",
    val title: String = "",
    val createdAt: Long = 0,
    val content: String = "",
    val authors: List<String> = emptyList(),
    val gallery: List<String> = emptyList(),
    var id: String = "",
)
