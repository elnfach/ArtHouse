package com.elnfach.arthouse.create.newsItem

data class CreateNewsItemState(
    val value: Boolean? = null,
    val error: String = "",
    val loading: Boolean = false
)
