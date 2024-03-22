package com.elnfach.arthouse.model

import com.google.firebase.Timestamp

data class NewsArticle(
    val image: String = "",
    val title: String = "",
    val createdAt: Timestamp = Timestamp.now(),
    val content: String = "",
    val authors: List<String> = emptyList(),
    val gallery: List<String> = emptyList(),
    var id: String = "",
)
